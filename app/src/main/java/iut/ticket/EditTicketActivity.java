package iut.ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import iut.ticket.dao.AppDB;
import iut.ticket.dao.TicketWithProducts;

public class EditTicketActivity extends MenuedActivity {

    private TicketWithProducts ticketWithProducts;

    private TextView idTicket;
    private TextView totalTicket;
    private ListView listProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ticket);

        this.ticketWithProducts = (TicketWithProducts) getIntent().getSerializableExtra("ticket");

        (this.idTicket = findViewById(R.id.idTicket)).setText(this.ticketWithProducts.ticket.ticket_id + "");
        (this.totalTicket = findViewById(R.id.totalTicket)).setText(getString(R.string.total, this.ticketWithProducts.total()));

        if(this.ticketWithProducts.ticket.nom_magasin.equals(getString(R.string.leclerc))){
            ((RadioButton) findViewById(R.id.leclerc)).setChecked(true);
        } else if(this.ticketWithProducts.ticket.nom_magasin.equals(getString(R.string.carefour))){
            ((RadioButton) findViewById(R.id.carouf)).setChecked(true);
        } else if(this.ticketWithProducts.ticket.nom_magasin.equals(getString(R.string.casino))){
            ((RadioButton) findViewById(R.id.casino)).setChecked(true);
        } else {
            ((RadioButton) findViewById(R.id.autre)).setChecked(true);
        }

        ImageView imageView = findViewById(R.id.ticketPhoto);
        imageView.setImageBitmap(this.ticketWithProducts.ticket.getImage());

        findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDB.getDB(EditTicketActivity.this).ticketDao().delete(ticketWithProducts);
                finish();
            }
        });

        (this.listProducts = findViewById(R.id.listProducts)).setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, this.ticketWithProducts.products));
    }

    @Override
    protected void onStop(){
        super.onStop();
        //TODO Save
    }
}
