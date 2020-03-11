package iut.ticket.dao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;

@Entity
public class Ticket implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public long ticket_id;

    public String commentaire;
    public String nom_magasin;
    public byte[] image;

    public Ticket(){}

    public Ticket(String nom_magasin, Bitmap imageBitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG,100, stream);
        this.nom_magasin = nom_magasin;
        this.image = stream.toByteArray();
        this.commentaire = "";
    }

    public Bitmap getImage(){
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
