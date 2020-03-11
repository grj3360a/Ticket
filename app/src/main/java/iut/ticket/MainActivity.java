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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import iut.ticket.dao.AppDB;
import iut.ticket.dao.Product;
import iut.ticket.dao.Ticket;
import iut.ticket.dao.TicketWithProducts;

public class MainActivity extends AppCompatActivity {

    private final Random r = new Random();
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
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
            this.processImage(data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.returnToMainActivity:
                return true;
            case R.id.historyMenu:
                startActivity(new Intent(this, HistoryActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onTakePhoto(View v){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    public void processImage(Intent data){
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");

        RadioGroup group = findViewById(R.id.magasinGroup);
        String nom_magasin = ((RadioButton) findViewById(group.getCheckedRadioButtonId())).getText().toString();

        Product[] products = new Product[1 + r.nextInt(10)];

        Ticket t = new Ticket(nom_magasin, imageBitmap);//TODO Nom_magasin

        for (int i = 0; i < products.length; i++)
            products[i] = new Product(productExample.get(r.nextInt(productExample.size())), r.nextInt(10), (Math.round(r.nextDouble() * 100.0) / 100.0) + r.nextInt(30));

        AppDB.getDB(getApplicationContext()).ticketDao().insertTicketWithProducts(t, products);

        Toast.makeText(getApplicationContext(), getString(R.string.ticketAdded), Toast.LENGTH_SHORT).show();
    }
}
