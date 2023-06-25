package de.brinkhaus.vokabeltrainer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Main activity of the app.
 */
public class MainActivity extends AppCompatActivity {

    ArrayList<String> allVocabs = new ArrayList<>();
    private int correct = 0;
    private int incorrect = 0;
    private int counter = 3;
    private int numOfRounds = 0;


    /**
     * This method is called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button) findViewById(R.id.start_button);
        Button new_file = (Button) findViewById(R.id.new_file);

        setupVocabs();

        /*
         * This method directs the user to the trainer.
         * variables get send to ViewInformation
         */
        start.setOnClickListener(v -> {
            Intent a = new Intent(MainActivity.this, ViewInformation.class);
            a.putExtra("allVocabs",allVocabs);
            startActivity(a);
        });

        /*Intent d = getIntent();
        correct = d.getExtras().getInt("Correct");
        incorrect = d.getExtras().getInt("Incorrect");
        counter = d.getExtras().getInt("Counter");
        numOfRounds = d.getExtras().getInt("Runden");*/
    }
    public void inputFile(View view){
        Intent aa = new Intent(MainActivity.this, InputDataExtern.class);
        startActivity(aa);
    }

    /**
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
                fileOutputStream.write("hello;hallo;0\nyou;du;0\nhuman;Mensch;0\ntrain;zug;0\niron;eisen;0\nfox;fuchs;0\nmobile phone;handy;0\nbucket;eimer;0".getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}