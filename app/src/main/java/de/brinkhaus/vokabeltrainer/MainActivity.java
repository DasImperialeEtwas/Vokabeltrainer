package de.brinkhaus.vokabeltrainer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    /**
     * This method is called when the activity is first created.
     */
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button) findViewById(R.id.start_button);
        Button new_file = (Button) findViewById(R.id.new_file);
        ImageView picture = (ImageView) findViewById(R.id.picture_start);

        setupVocabs();

        /**
         * This method directs the user to the trainer.
         * variables get send to ViewInformation
         */
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, ViewInformation.class);
                a.putExtra("allVocabs",allVocabs);
                startActivity(a);
            }
        });
    }

    /**
     * Check if files exist, if not create them
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
                fileOutputStream.write("W1;Hello;0\nW2;du;0\nW3;Mensch;0\nW4;zug;0".getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}