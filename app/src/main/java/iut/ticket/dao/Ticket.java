package iut.ticket.dao;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

@Entity
public class Ticket {

    @PrimaryKey
    public int id;

    @Relation(parentColumn =  "Product.ticket_id", entityColumn = "Product.id")
    public List<Product> products;
}
