package iut.ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

        if(AppDB.getTicketDao().isEmpty()){
            this.listView.setVisibility(View.INVISIBLE);
            findViewById(R.id.emptyInfo).setVisibility(View.VISIBLE);
        } else {
            this.listView.setVisibility(View.VISIBLE);
            findViewById(R.id.emptyInfo).setVisibility(View.INVISIBLE);
        }

        //Re-defining a new TicketAdapter to remove bugged state when changing activity
        this.listView.setAdapter(new TicketAdapter(getApplicationContext()));
    }

    public void onFloatingPhotoButton(View v){
        startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

}
