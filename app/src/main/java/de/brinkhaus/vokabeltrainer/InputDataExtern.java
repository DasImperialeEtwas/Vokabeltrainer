package de.brinkhaus.vokabeltrainer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
        language1.setOnClickListener(v -> language1.setText(""));
        language2.setOnClickListener(v -> language2.setText(""));
        submit.setOnClickListener(v -> inputVocabs());

        String value1 = language1.getText().toString();
    }

    private void inputVocabs() {
        if(language1.getText().toString().equals("") || language2.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Du solltest halt schon was eingeben ;) ", Toast.LENGTH_LONG).show();
        }else{
            try {
                File datafile = new File(Environment.getExternalStoragePublicDirectory("/Voctrainer/").getAbsolutePath(), "vocabulary.csv");
                FileOutputStream fileOutputStream = new FileOutputStream(datafile,true);
           /* FileWriter fileWriter = new FileWriter(datafile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             bufferedWriter.write("\n"+language1.getText().toString() + ";" + language2.getText().toString() + ";" + "0");
*/
                fileOutputStream.write(("\n"+language1.getText().toString() + ";" + language2.getText().toString() + ";" + "0").getBytes());
                Toast.makeText(getApplicationContext(),"Speichern erfolgreich", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Speichern nicht erfolgreich", Toast.LENGTH_SHORT).show();
                throw new RuntimeException(e);
            }
            language1.setText("");
            language2.setText("");
        }
        }

}
/*
File datadirectory = Environment.getExternalStoragePublicDirectory("/VocTrainer/");
            File datafile = new File(datadirectory, "vocabulary.csv");
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(datafile);
                String tmp ="";
                for(int x = 0; x<allVocabs.size(); x++){
                    tmp += allVocabs.get(x)+"\n";
                }
                fileOutputStream.write(tmp.getBytes());

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            startActivity((b));





 */
