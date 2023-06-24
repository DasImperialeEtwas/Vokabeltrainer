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
    ArrayList<String> box_1 = new ArrayList<>();
    ArrayList<String> box_2 = new ArrayList<>();
    ArrayList<String> box_3 = new ArrayList<>();
    ArrayList<String> box_4 = new ArrayList<>();
    ArrayList<String> box_5 = new ArrayList<>();
    ArrayList<String> box_6 = new ArrayList<>();

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
                a.putExtra("Lernbox 1",box_1);
                a.putExtra("Lernbox 2",box_2);
                a.putExtra("Lernbox 3",box_3);
                a.putExtra("Lernbox 4",box_4);
                a.putExtra("Lernbox 5",box_5);
                a.putExtra("Lernbox 6",box_6);
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
        File lernbox_1 = new File(datadirectory, "box_1.csv");
        File lernbox_2 = new File(datadirectory, "box_2.csv");
        File lernbox_3 = new File(datadirectory, "box_3.csv");
        File lernbox_4 = new File(datadirectory, "box_4.csv");
        File lernbox_5 = new File(datadirectory, "box_5.csv");
        File lernbox_6 = new File(datadirectory, "box_6.csv");
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

                //Lernbox_1
                fileInputStream = new FileInputStream(lernbox_1);
                scanner = new Scanner(fileInputStream);

                while(scanner.hasNextLine()){
                    box_1.add(scanner.nextLine());
                }

                //Lernbox_2
                fileInputStream = new FileInputStream(lernbox_2);
                scanner = new Scanner(fileInputStream);

                while(scanner.hasNextLine()){
                    box_2.add(scanner.nextLine());
                }

                //Lernbox_3
                fileInputStream = new FileInputStream(lernbox_3);
                scanner = new Scanner(fileInputStream);

                while(scanner.hasNextLine()){
                    box_3.add(scanner.nextLine());
                }

                //Lernbox_4
                fileInputStream = new FileInputStream(lernbox_4);
                scanner = new Scanner(fileInputStream);

                while(scanner.hasNextLine()){
                    box_4.add(scanner.nextLine());
                }

                //Lernbox_5
                fileInputStream = new FileInputStream(lernbox_5);
                scanner = new Scanner(fileInputStream);

                while(scanner.hasNextLine()){
                    box_5.add(scanner.nextLine());
                }

                //Lernbox_6
                fileInputStream = new FileInputStream(lernbox_6);
                scanner = new Scanner(fileInputStream);

                while(scanner.hasNextLine()){
                    box_6.add(scanner.nextLine());
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
                lernbox_1.createNewFile();
                lernbox_2.createNewFile();
                lernbox_3.createNewFile();
                lernbox_4.createNewFile();
                lernbox_5.createNewFile();
                lernbox_6.createNewFile();

                //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Environment.openFileOutput(datafile));
                FileOutputStream fileOutputStream = new FileOutputStream(datafile);
                fileOutputStream.write("1;Hello\n2;du\n3;Mensch\n4;zug".getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}