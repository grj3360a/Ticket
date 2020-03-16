package iut.ticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import iut.ticket.dao.AppDB;
import iut.ticket.dao.Product;
import iut.ticket.dao.Ticket;
import iut.ticket.dao.TicketWithProducts;

public class MainActivity extends MenuedActivity {

    private final Random r = new Random();
    private static List<String> productExample = Arrays.asList(
            "Brique de lait", "Fanta", "Coca", "Carotte", "Viande bovine",
            "Petit pois", "Corn flakes", "Blanc de poulet", "Rillettes", "DVD Collector Arnault Schwacenégeur",
            "Les Chevaliers du Fiel MAX Edition", "Velouté aux fruits", "Tomates", "Cerises", "Bolognaise",
            "Spaghetti", "Pâtes", "Coucous"
    );
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PERMISSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDB.createDB(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView nbTickets = findViewById(R.id.nbTickets);
        TextView totalTickets = findViewById(R.id.totalTickets);
        int nbTicket = AppDB.getTicketDao().getTicketWithProducts().size();

        nbTickets.setText(getResources().getQuantityString(R.plurals.tickets, nbTicket, nbTicket));
        totalTickets.setText(getString(R.string.total, AppDB.getTicketDao().total()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
            this.processImage(data);
    }

    /**
     * OnClick on the main photo button
     */
    public void onTakePhoto(View v){
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION);
            return;
        }

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    /**
     * Process the image contained in "Intent data"
     */
    public void processImage(Intent data){
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");

        RadioGroup group = findViewById(R.id.magasinGroup);
        String nom_magasin = ((RadioButton) findViewById(group.getCheckedRadioButtonId())).getText().toString();

        /*
        Après de multiple tentative d'utiliser l'OCR de google afin de reconnaitre le texte des tickets,
        nous avons préferer créer des produits fictifs dans le but de démontrer l'utiliter d'une
        tel application.
         */

        Product[] products = new Product[1 + r.nextInt(10)];
        Ticket t = new Ticket(nom_magasin, imageBitmap);

        for (int i = 0; i < products.length; i++)
            products[i] = new Product(productExample.get(r.nextInt(productExample.size())), r.nextInt(3) + 1, (Math.round(r.nextDouble() * 100.0) / 100.0) + r.nextInt(30));

        AppDB.getTicketDao().insertTicketWithProducts(t, products);

        Toast.makeText(getApplicationContext(), getString(R.string.ticketAdded), Toast.LENGTH_SHORT).show();
    }
}
