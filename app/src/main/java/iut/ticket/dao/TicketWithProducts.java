package iut.ticket.dao;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TicketWithProducts {
    @Embedded
    public Ticket user;
    @Relation(
            parentColumn = "ticket_id",
            entityColumn = "product_id"
    )

    public List<Product> products;

    public double total(){
        double total = 0D;

        for(Product p : products){
            total += p.price * p.count;
        }

        return total;
    }
}

