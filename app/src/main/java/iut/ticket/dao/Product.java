package iut.ticket.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

@Entity
public class Product {

    @PrimaryKey(autoGenerate = true)
    public int product_id;

    public int ticket_id;
    String name;
    int count;
    double price;

    public Product(String name, int count, double price){
        this.name = name;
        this.count = count;
        this.price = price;
    }
}
