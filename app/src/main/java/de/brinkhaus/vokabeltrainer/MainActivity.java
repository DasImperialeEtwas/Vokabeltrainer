package de.brinkhaus.vokabeltrainer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/**
 * Main activity of the app.
 */
public class MainActivity extends AppCompatActivity {

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

        /**
         * Check if files exist, if not create them
         */

        File datadirectory = Environment.getExternalStoragePublicDirectory("/VocTrainer/");
        File datafile = new File(datadirectory, "vocabulary.csv");
        Log.i("Vocabeltrainer: " , datafile.getAbsolutePath());

        if (datafile.exists()){
            Log.i("Vocabeltrainer: " , "File existiert");
        }
        else {
            //Create Folder and Example File
            try {
                datadirectory.mkdir();
                datafile.createNewFile();
                //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Environment.openFileOutput(datafile));
                FileOutputStream fileOutputStream = new FileOutputStream(datafile);
                fileOutputStream.write("1;Hello\n2;du\n3;Mensch\n4;zug".getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * This method directs the user to the trainer.
         */
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, ViewInformation.class);
                startActivity(a);
            }
        });
    }
}