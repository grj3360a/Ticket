package iut.ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class CreditActivity extends MenuedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
    }

    public void onClickImage(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.iut_link))));
    }
}
