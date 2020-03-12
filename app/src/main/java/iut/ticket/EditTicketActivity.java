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

public class EditTicketActivity extends AppCompatActivity {

    private TicketWithProducts ticketWithProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ticket);

        this.ticketWithProducts = (TicketWithProducts) getIntent().getSerializableExtra("ticket");

        ((TextView) findViewById(R.id.idTicket)).setText(this.ticketWithProducts.ticket.ticket_id + "");
        ((TextView) findViewById(R.id.totalTicket)).setText(getString(R.string.total, this.ticketWithProducts.total()));

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

        ListView listView = findViewById(R.id.listProducts);
        listView.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, this.ticketWithProducts.products));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.returnToMainActivity:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.historyMenu:
                startActivity(new Intent(this, HistoryActivity.class));
                return true;
            case R.id.creditMenu:
                startActivity(new Intent(this, CreditActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
