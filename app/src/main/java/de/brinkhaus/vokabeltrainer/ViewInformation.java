package de.brinkhaus.vokabeltrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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

    int language;
    String[] question;
    String[] wrongAnswers;


    Button eins;
    Button zwei;
    Button drei;
    Button vier;


    TextView vocabel;
    TextView anweisung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_information);

        Intent a = getIntent();

        allVocabs = a.getExtras().getStringArrayList("allVocabs");

        Log.i("Vocabs",String.valueOf(allVocabs.size()));

        eins = (Button) findViewById(R.id.answer_1);
        zwei = (Button) findViewById(R.id.answer_2);
        drei = (Button) findViewById(R.id.answer_3);
        vier = (Button) findViewById(R.id.answer_4);

        vocabel = (TextView) findViewById(R.id.vocabel);
        anweisung = (TextView) findViewById(R.id.anweisung);
        chooseWord();
    }

    public void checkForWinner(View view, Button btn){
        //Dissable Buttons
        eins.setEnabled(false);
        zwei.setEnabled(false);
        drei.setEnabled(false);
        vier.setEnabled(false);

        //Wenn es Richtig ist
        if (btn.getText().equals(question[language])){
            btn.setBackgroundColor(Color.argb(255,0,255,0));
            question[2] = String.valueOf(Integer.parseInt(question[2]) +1);
        }else{
            //Wenn es Falsch ist
            question[2] = "0";
            btn.setBackgroundColor(Color.argb(255,255,0,0));

            if(eins.getText().equals(question[language])){
                eins.setBackgroundColor(Color.argb(255,0,255,0));
            }
            if(zwei.getText().equals(question[language])){
                zwei.setBackgroundColor(Color.argb(255,0,255,0));
            }
            if(drei.getText().equals(question[language])){
                drei.setBackgroundColor(Color.argb(255,0,255,0));
            }

            if(vier.getText().equals(question[language])){
                vier.setBackgroundColor(Color.argb(255,0,255,0));
            }
        }

        //Restore Changes
        allVocabs.add(wrongAnswers[0]);
        allVocabs.add(wrongAnswers[0]);
        allVocabs.add(wrongAnswers[0]);
        allVocabs.add(question[0]+";"+question[1]+";"+question[2]);
        Log.i("Vocab","Restore Done");
    }

    public void btn1(View view){
        checkForWinner(view,eins);
    }
    public void btn2(View view){
        checkForWinner(view,zwei);
    }
    public void btn3(View view){
        checkForWinner(view,drei);
    }
    public void btn4(View view){
        checkForWinner(view,vier);
    }

    public void chooseWord(){
        Random random = new Random();
        language = random.nextInt(2);

        int questionNumber = random.nextInt(allVocabs.size());
        boolean search = true;
        //Englisch; Deutsch; Box

        while(search){
            question = allVocabs.get(questionNumber).split(";");
            allVocabs.remove(questionNumber);
            if(Integer.parseInt(question[2]) != 6){
                search = false;
            }
        }
        wrongAnswers = new String[3];
        for(int r=0; r<3;r++){
            int tmp = random.nextInt(allVocabs.size());
            wrongAnswers[r] = allVocabs.get(tmp);
            allVocabs.remove(tmp);
        }


        Log.i("Vocab","hui");

        int correctButton = random.nextInt(4);

        switch (correctButton){
            case 0:
                eins.setText(question[language]); //Richtige Lösung
                zwei.setText(wrongAnswers[0].split(";")[language]);
                drei.setText(wrongAnswers[1].split(";")[language]);
                vier.setText(wrongAnswers[2].split(";")[language]);
                break;
            case 1:
                eins.setText(wrongAnswers[0].split(";")[language]);
                zwei.setText(question[language]); //Richtige Lösung
                drei.setText(wrongAnswers[1].split(";")[language]);
                vier.setText(wrongAnswers[2].split(";")[language]);
                break;
            case 2:
                eins.setText(wrongAnswers[0].split(";")[language]);
                zwei.setText(wrongAnswers[1].split(";")[language]);
                drei.setText(question[language]); //Richtige Lösung
                vier.setText(wrongAnswers[2].split(";")[language]);
                break;
            case 3:
                eins.setText(wrongAnswers[0].split(";")[language]);
                zwei.setText(wrongAnswers[1].split(";")[language]);
                drei.setText(wrongAnswers[2].split(";")[language]);
                vier.setText(question[language]); //Richtige Lösung
                break;
        }
        if(language == 0)
            vocabel.setText(question[1]);
        else vocabel.setText(question[0]);

        //Enable Buttons
        eins.setEnabled(true);
        eins.setHighlightColor(Color.argb(100,1,135,134));
        zwei.setEnabled(true);
        zwei.setHighlightColor(Color.argb(100,1,135,134));
        drei.setEnabled(true);
        drei.setHighlightColor(Color.argb(100,1,135,134));
        vier.setEnabled(true);
        vier.setHighlightColor(Color.argb(100,1,135,134));
    }
}