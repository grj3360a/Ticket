package iut.ticket.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

@Entity
public class Product implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public long product_id;

    public long ticket_id;
    public String name;
    public int count;
    public double price;

    public Product(String name, int count, double price){
        this.name = name;
        this.count = count;
        this.price = price;
    }

    @Override
    public String toString(){
        return this.name + " x" + count;
    }
}
