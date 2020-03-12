package iut.ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import iut.ticket.dao.AppDB;

public class HistoryActivity extends AppCompatActivity {

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
                return true;
            case R.id.creditMenu:
                startActivity(new Intent(this, CreditActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
