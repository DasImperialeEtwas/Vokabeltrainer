package de.brinkhaus.vokabeltrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class InputDataExtern extends AppCompatActivity {
    ArrayList<String> allVocabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_extern);

        EditText language1 = (EditText) findViewById(R.id.language1);
        EditText language2 = (EditText) findViewById(R.id.language2);
        Button submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String sprache1 = language1.getText().toString();
               String sprache2 = language2.getText().toString();
               String spalte3 = "1";

            }
        });
    }
    public void backStart(View view) {
        Intent dataa = new Intent(InputDataExtern.this, MainActivity.class);
        dataa.putExtra("allVocabs", allVocabs);
        startActivity(dataa);
    }
}