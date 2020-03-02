package iut.ticket.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Product {

    @PrimaryKey
    public int id;

    public int ticket_id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "count")
    int count;

    @ColumnInfo(name = "price")
    double price;

    public Product(String name, int count, double price){
        this.name = name;
        this.count = count;
        this.price = price;
    }
}
