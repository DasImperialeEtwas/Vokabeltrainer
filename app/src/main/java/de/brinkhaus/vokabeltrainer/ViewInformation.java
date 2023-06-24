package de.brinkhaus.vokabeltrainer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Random;

/**
 * Main activity of the app.
 */

public class ViewInformation extends AppCompatActivity {

    /**
     * This method is called when the activity is first created.
     *
     * After API 23 the permission request for accessing external storage is changed
     * Before API 23 permission request is asked by the user during installation of app
     * After API 23 permission request is asked at runtime
     * */
    private int EXTERNAL_STORAGE_PERMISSION_CODE = 23;
    private int counter = 20;
    private ArrayList<String> allVocabs;
    private ArrayList<String> box_1;
    private ArrayList<String> box_2;
    private ArrayList<String> box_3;
    private ArrayList<String> box_4;
    private ArrayList<String> box_5;
    private ArrayList<String> box_6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_information);

        Intent a = getIntent();
        allVocabs = a.getExtras().getStringArrayList("allVocabs");
        box_1 = a.getExtras().getStringArrayList("Lernbox 1");
        box_2 = a.getExtras().getStringArrayList("Lernbox 2");
        box_3 = a.getExtras().getStringArrayList("Lernbox 3");
        box_4 = a.getExtras().getStringArrayList("Lernbox 4");
        box_5 = a.getExtras().getStringArrayList("Lernbox 5");
        box_6 = a.getExtras().getStringArrayList("Lernbox 6");

        Log.i("Vocabs",String.valueOf(allVocabs.size()));

        Button eins = (Button) findViewById(R.id.answer_1);
        Button zwei = (Button) findViewById(R.id.answer_2);
        Button drei = (Button) findViewById(R.id.answer_3);
        Button vier = (Button) findViewById(R.id.answer_4);

        TextView vocabel = (TextView) findViewById(R.id.vocabel);
        TextView anweisung = (TextView) findViewById(R.id.anweisung);



    }
}