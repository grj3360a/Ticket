package iut.ticket.dao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Entity
public class Ticket {

    @PrimaryKey(autoGenerate = true)
    public int ticket_id;

    public String nom_magasin;
    public byte[] image;

    public Ticket(){}

    public Ticket(Bitmap imageBitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG,100, stream);
        this.image = stream.toByteArray();
    }

    public Bitmap getImage(){
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
