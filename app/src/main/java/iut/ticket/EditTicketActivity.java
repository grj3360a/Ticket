package iut.ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

        ImageView imageView = findViewById(R.id.ticketPhoto);
        imageView.setImageBitmap(this.ticketWithProducts.ticket.getImage());

        findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDB.getDB(EditTicketActivity.this).ticketDao().delete(ticketWithProducts);
                finish();
            }
        });
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
