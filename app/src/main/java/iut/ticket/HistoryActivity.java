package iut.ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import iut.ticket.dao.AppDB;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView listView = findViewById(R.id.listHistory);
        listView.setAdapter(new TicketAdapter(AppDB.getDB(getApplicationContext()).ticketDao().getTicketWithProducts(), getApplicationContext()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.returnToMainActivity:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.historyMenu:
                return true;
            case R.id.settingsMenu:
                //TODO
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
