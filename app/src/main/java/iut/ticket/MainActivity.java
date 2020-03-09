package iut.ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import iut.ticket.dao.AppDB;
import iut.ticket.dao.Product;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            this.processImage(data);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.returnToMainActivity:
                return true;
            case R.id.historyMenu:
                startActivity(new Intent(this, HistoryActivity.class));
                return true;
            case R.id.settingsMenu:
                //TODO
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onTakePhoto(View v){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void processImage(Intent data){
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");

        Random r = new Random();
        List<Product> products = new ArrayList<>();

        for(int nbProduct = 0; nbProduct < 1 + r.nextInt(10); nbProduct++)
            products.add(new Product(productExample.get(r.nextInt(productExample.size())), r.nextInt(10), (Math.round(r.nextDouble() * 100.0) / 100.0) + r.nextInt(30)));

        AppDB.getDB(getApplicationContext()).ticketDao().insertTicketWithProducts(new Ticket(imageBitmap), products);
    }
}
