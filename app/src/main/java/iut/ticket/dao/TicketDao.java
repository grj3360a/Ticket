package iut.ticket.dao;

import android.util.Log;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public abstract class TicketDao {

    public void insertTicketWithProducts(Ticket ticket, Product... products){
        ticket.ticket_id = insert(ticket);

        for(Product product : products)
            product.ticket_id = ticket.ticket_id;

        insertAll(products);
    }

    @Insert
    public abstract void insertAll(Product... products);

    @Transaction
    @Query("SELECT * FROM Ticket")
    public abstract List<TicketWithProducts> getTicketWithProducts();

    @Transaction
    @Query("SELECT * FROM Ticket WHERE ticket_id = :ticket_id")
    public abstract TicketWithProducts getTicketWithProductsFromID(int ticket_id);

    @Query("SELECT * FROM Product")
    public abstract List<Product> getAllProducts();

    @Insert
    public abstract long insert(Ticket ticket);

    public void delete(TicketWithProducts ticketWithProducts){
        for(Product p : ticketWithProducts.products){
            delete(p);
        }
        delete(ticketWithProducts.ticket);
    }

    @Delete
    public abstract void delete(Ticket ticket);

    @Delete
    public abstract void delete(Product product);
}

