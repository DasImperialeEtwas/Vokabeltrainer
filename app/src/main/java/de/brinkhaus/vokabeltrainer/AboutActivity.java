package de.brinkhaus.vokabeltrainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * This activity shows the about information.
 */
public class AboutActivity extends AppCompatActivity {

    String versionName = BuildConfig.VERSION_NAME;
    String actionBar = "About";

/**
 * This method is called when the activity is created.
 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView vn = (TextView) findViewById(R.id.versionName);
        TextView about_me = (TextView) findViewById(R.id.aboutMe);

        vn.setText(String.format(getString(R.string.version), versionName));
        about_me.setText(getString(R.string.about_text));

        Toolbar toolbar = findViewById(R.id.toolbar_about);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    /**
     * This method is called when the user clicks the back button.
     *
     * @return True if the event was handled, false otherwise.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}