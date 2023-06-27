package de.brinkhaus.vokabeltrainer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Main activity of the app.
 */
public class MainActivity extends AppCompatActivity {

    ArrayList<String> allVocabs = new ArrayList<>();
    private final int counter = 6;

    /*
     * This method is called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.start_button);
        final ProgressBar pbRight = findViewById(R.id.progressBar_right);
        final ProgressBar pbFalse = findViewById(R.id.progressBar_false);
        final ProgressBar pBAll = findViewById(R.id.progressBar_all);

        pbFalse.setProgress(0);
        pbRight.setProgress(0);
        pBAll.setProgress(0);

        pbRight.setMax(counter);
        pbFalse.setMax(counter);
        pBAll.setMax(21);
        pbFalse.setProgress(2);
        pbRight.setProgress(4);
        pBAll.setProgress(counter);

        setupVocabs();

        /*
         * This method directs the user to the trainer.
         * variables get send to TrainingActivity
         */
        start.setOnClickListener(v -> {
            Intent a = new Intent(MainActivity.this, TrainingActivity.class);
            a.putExtra("allVocabs",allVocabs);
            a.putExtra("counter", counter);
            startActivity(a);
        });
    }

    /*
     * Check if files exist, if not create them
     * Directory gets created, File gets created.
     *Line 70: scanner reads the csv file and adds them to "allVocabs"
     */

    private void setupVocabs(){
        File datadirectory = Environment.getExternalStoragePublicDirectory("/VocTrainer/");
        File datafile = new File(datadirectory, "vocabulary.csv");
        Log.i("Vocabeltrainer: " , datafile.getAbsolutePath());

        if (datafile.exists()){
            Log.i("Vocabeltrainer: " , "File existiert");
            try {
                //AllVocabs
                FileInputStream fileInputStream = new FileInputStream(datafile);
                Scanner scanner = new Scanner(fileInputStream);

                while(scanner.hasNextLine()){
                    allVocabs.add(scanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            //Create Folder and example files
            try {
                datadirectory.mkdir();
                datafile.createNewFile();

                //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Environment.openFileOutput(datafile));
                FileOutputStream fileOutputStream = new FileOutputStream(datafile);
                fileOutputStream.write(("cerca de;zirka;0\ncada ;jeders;0\nandaluz ;andalusisch;0\ncausar algo ;etw. verursachen;0\n" +
                        "el medio ambiente;die Umwelt;0\nel pasado;die Vergangenheit;0\nel/la periodista ;Journalist/in;0\nsostenible ;nachhaltig;0\n " +
                        "la situacion ;die Situation;0\npor suerte ;zum Glück;0\nen aquel entonces ;damals;0\nla costa ;die Küste;0\n" +
                        "la solución ;die Lösung;0\nel conflicto ;der Konflikt;0\nprimero ;erstens;0\n " +
                        "intentar ;versuchen;0\nla basura ;der Müll;0\nreciclar algo ;etw. recyceln;0\n " +
                        "la toalla ;Handtuch;0\nahorra ;sparen;0\nintentar ;versuchen;0\nprimero ;erstens;0").getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void inputFile(View view){
        Intent aa = new Intent(MainActivity.this, InputDataExtern.class);
        startActivity(aa);
    }

    /*
     * Menu gets created and linked to the about page
     * Build with the help of a Tutorial by www.riptutorial.com
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about_menu) {
            Intent about = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(about);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        setupVocabs();
        super.onResume();
    }
}