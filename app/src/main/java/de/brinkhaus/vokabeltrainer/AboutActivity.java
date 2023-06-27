package de.brinkhaus.vokabeltrainer;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/*
 * This activity shows the about information.
 */
public class AboutActivity extends AppCompatActivity {

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