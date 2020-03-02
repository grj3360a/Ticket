package iut.ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import iut.ticket.dao.AppDB;
import iut.ticket.dao.Ticket;

public class MainActivity extends AppCompatActivity {

    private static List<String> productExample = Arrays.asList(
            "Brique de lait", "Fanta", "Coca", "Carotte", "Viande bovine",
            "Petit pois", "Corn flakes", "Blanc de poulet", "Rillettes", "DVD Collector Arnault Schwacenégeur",
            "Les Chevaliers du Fiel MAX Edition", "Velouté aux fruits", "Tomates", "Cerises", "Bolognaise",
            "Spaghetti", "Pâtes", "Coucous"
    );
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void onTakePhoto(View v){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            this.processImage(data);
        }
    }

    public void processImage(Intent data){
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");

        Random r = new Random();
        List<Ticket.Product> products = new ArrayList<>();

        for(int nbProduit = 0; nbProduit < 1 + r.nextInt(10); nbProduit++){
            products.add(new Ticket.Product(productExample.get(r.nextInt(productExample.size())), r.nextInt(10), (Math.round(r.nextDouble()*100.0)/100.0) + r.nextInt(30)));
        }

        AppDB.getDB(getApplicationContext()).ticketDao().insertAll(new Ticket());
    }
}
