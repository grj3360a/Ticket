package iut.ticket.dao;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

public class TicketWithProducts implements Serializable {
    @Embedded
    public Ticket ticket;

    @Relation(
            parentColumn = "ticket_id",
            entityColumn = "ticket_id"
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

