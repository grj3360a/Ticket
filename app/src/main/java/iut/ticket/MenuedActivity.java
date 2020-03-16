package iut.ticket;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Simplify the implementation of menu in all activities by unifying all in one class.
 * This class add the menu layout to the AppCompat Activity
 */
public class MenuedActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        if(this instanceof MainActivity)//Don't re-open the same activity from the menu
            menu.findItem(R.id.photoMenu).setVisible(false);

        if(this instanceof HistoryActivity)//Don't re-open the same activity from the menu
            menu.findItem(R.id.historyMenu).setVisible(false);

        if(this instanceof CreditActivity)//Don't re-open the same activity from the menu
            menu.findItem(R.id.creditMenu).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Which item is selected ?
        switch (item.getItemId()) {
            case R.id.photoMenu:
                startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

            case R.id.historyMenu:
                startActivity(new Intent(this, HistoryActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

            case R.id.creditMenu:
                startActivity(new Intent(this, CreditActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
