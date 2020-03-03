package iut.ticket.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public abstract class TicketDao {

    public void insertTicketWithProducts(Ticket ticket, List<Product> products){
        for(Product product : products){
            product.ticket_id = ticket.ticket_id;
        }

        insertAll(products);
    }

    @Insert
    public abstract void insertAll(List<Product> pets);

    @Transaction
    @Query("SELECT * FROM Ticket")
    public abstract List<TicketWithProducts> getTicketWithProducts();

    @Insert
    public abstract void insertAll(Ticket... tickets);

    @Delete
    public abstract void delete(Ticket user);
}

