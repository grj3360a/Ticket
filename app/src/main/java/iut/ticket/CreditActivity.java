package iut.ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CreditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
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
