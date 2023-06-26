package de.brinkhaus.vokabeltrainer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class InputDataExtern extends AppCompatActivity {
    ArrayList<String> allVocabs = new ArrayList<>();
    EditText language1;
    EditText language2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_extern);

        language1 = (EditText) findViewById(R.id.language1);
        language2 = (EditText) findViewById(R.id.language2);
        Button submit = (Button) findViewById(R.id.submit);

        /*
         *EditText field go empty when clicked
         * submitbutton starts method
         */
        language1.setOnClickListener(v -> language1.setText(" "));
        language2.setOnClickListener(v -> language2.setText(" "));
        submit.setOnClickListener(v -> inputVocabs());
    }

    private void inputVocabs() {
        try {
            File datafile = new File(Environment.getExternalStoragePublicDirectory("/Voctrainer/").getAbsolutePath(), "vocabulary.csv");
            FileOutputStream fileOutputStream = new FileOutputStream(datafile);
            //fileOutputStream.write("pillow;kissen;0\nwindow;fenster;0\nbear;Bär;0\ncandle;Kerze;0\nnitron;Nitrat;0\ntablet;Tablet;0\nrunning;rennen;0\nwalking;laufen;0".getBytes());
            //fileOutputStream.write();
            Toast.makeText(getApplicationContext(),"Speichern erfolgreich", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Speichern nicht erfolgreich", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
        language1.setText("");
        language2.setText("");
    }
}
/*
fileOutputStream.write(Integer.parseInt(value1 + ";" + value2 + ";" + "0".getBytes()));

 */
