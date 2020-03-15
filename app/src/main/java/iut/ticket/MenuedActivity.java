package iut.ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Simplify the implementation of menu in all activities by unifying all in one class.
 * This class add the menu layout to the AppCompat Activity
 */
public class MenuedActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Which item is selected ?
        switch (item.getItemId()) {
            case R.id.returnToMainActivity:
                if(this instanceof MainActivity)//Don't re-open the same activity from the menu
                    return true;
                startActivity(new Intent(this, MainActivity.class));
                return true;

            case R.id.historyMenu:
                if(this instanceof HistoryActivity)//Don't re-open the same activity from the menu
                    return true;
                startActivity(new Intent(this, HistoryActivity.class));
                return true;

            case R.id.creditMenu:
                if(this instanceof CreditActivity)//Don't re-open the same activity from the menu
                    return true;
                startActivity(new Intent(this, CreditActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
