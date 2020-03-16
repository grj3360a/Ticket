package iut.ticket;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import iut.ticket.dao.AppDB;
import iut.ticket.dao.TicketWithProducts;

public class ListTicketActivity extends MenuedActivity implements View.OnClickListener {

    private static final int EDITED_TICKET = 56456;

    private TicketAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        this.listView = findViewById(R.id.listHistory);

        this.listView.setAdapter(this.adapter = new TicketAdapter(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(AppDB.getTicketDao().isEmpty()){
            this.listView.setVisibility(View.INVISIBLE);
            findViewById(R.id.emptyInfo).setVisibility(View.VISIBLE);
        } else {
            this.listView.setVisibility(View.VISIBLE);
            findViewById(R.id.emptyInfo).setVisibility(View.INVISIBLE);
        }
    }

    public void onFloatingPhotoButton(View v){
        startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    /**
     * On click any element of the list
     */
    @Override
    public void onClick(View v) {
        int idTicket = Integer.parseInt(((TextView) v.findViewById(R.id.idTicket)).getText().toString());
        TicketWithProducts ticketWithProducts = AppDB.getTicketDao().getTicketWithProductsFromID(idTicket);

        Intent in = new Intent(this, EditTicketActivity.class);
        in.putExtra("ticket", ticketWithProducts);
        startActivityForResult(in, 0);//Wait for result to update data
    }

    /**
     * Update arrayAdapter data on edit return
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.adapter.clear();
        this.adapter.addAll(AppDB.getTicketDao().getTicketWithProducts());
        this.adapter.notifyDataSetChanged();
    }
}
