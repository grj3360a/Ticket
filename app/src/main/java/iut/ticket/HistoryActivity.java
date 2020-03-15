package iut.ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import iut.ticket.dao.AppDB;

public class HistoryActivity extends MenuedActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.listView = findViewById(R.id.listHistory);
        this.listView.setAdapter(new TicketAdapter(getApplicationContext()));
    }

}
