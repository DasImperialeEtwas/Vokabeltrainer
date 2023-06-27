package de.brinkhaus.vokabeltrainer;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * Datainput activity of the app.
 */

public class InputDataExtern extends AppCompatActivity {
    /*
     * This method is called when the activity is first created.
     */
    EditText language1;
    EditText language2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_extern);

        language1 = findViewById(R.id.language1);
        language2 = findViewById(R.id.language2);
        Button submit = findViewById(R.id.submit);

        /*
         *EditText field go empty when clicked
         * submitbutton starts method
         */
        language1.setOnClickListener(v -> language1.setText(""));
        language2.setOnClickListener(v -> language2.setText(""));
        submit.setOnClickListener(v -> inputVocabs());
    }

    private void inputVocabs() {
        if(language1.getText().toString().equals("") || language2.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Du solltest halt schon was eingeben ;) ", Toast.LENGTH_LONG).show();
        }else{
            try {
                File datafile = new File(Environment.getExternalStoragePublicDirectory("/Voctrainer/").getAbsolutePath(), "vocabulary.csv");
                FileOutputStream fileOutputStream = new FileOutputStream(datafile,true);

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

