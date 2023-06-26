package de.brinkhaus.vokabeltrainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/*
 * This activity shows the about information.
 */
public class AboutActivity extends AppCompatActivity {

    String versionName = BuildConfig.VERSION_NAME;
    String actionBar = "About";
    Button back_button;

/*
 * This method is called when the activity is created.
 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void back(){
        Intent back = new Intent(AboutActivity.this, MainActivity.class);
        startActivity(back);
    }

}