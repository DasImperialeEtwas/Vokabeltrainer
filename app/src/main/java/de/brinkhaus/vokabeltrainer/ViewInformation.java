package de.brinkhaus.vokabeltrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private int counter = 3;
    private int numOfRounds = 0;
    private int correct = 0;
    private int incorrect = 0;
    private ArrayList<String> allVocabs;
    int language;
    String[] question;
    String[] wrongAnswers;
    Button one;
    Button two;
    Button three;
    Button four;
    TextView vocable;
    TextView instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_information);

        /*
         * Variables arrive in ViewInformation
         * Buttons get declared
         * other variables get declared
         */

        Intent a = getIntent();
        allVocabs = a.getExtras().getStringArrayList("allVocabs");
        Log.i("Vocabs",String.valueOf(allVocabs.size()));

        one = (Button) findViewById(R.id.answer_1);
        two = (Button) findViewById(R.id.answer_2);
        three = (Button) findViewById(R.id.answer_3);
        four = (Button) findViewById(R.id.answer_4);

        vocable = (TextView) findViewById(R.id.vocabel);
        instruction = (TextView) findViewById(R.id.anweisung);
        chooseWord();
    }

    /**
     * This method figure out which button holds the correct answer
     * Buttons get disabled to ensure that the answer can´t be changed
     */
    public void checkForWinner(View view, Button btn){
        one.setClickable(false);
        two.setClickable(false);
        three.setClickable(false);
        four.setClickable(false);

        Button next = findViewById(R.id.next_button);
        next.setVisibility(Button.VISIBLE);

        /**
         * This if-loop checks if the correct answer was choosen
         * Buttons change color to "green" if the answer is correct
         * Buttons change color to "red" if the answer is wrong
         */
        if (btn.getText().equals(question[language])){
            //True
            btn.setBackgroundColor(Color.argb(100,0,255,0));
            question[2] = String.valueOf(Integer.parseInt(question[2]) +1);
            correct++;
        }
        else{
            //Wrong
            question[2] = "0";
            btn.setBackgroundColor(Color.argb(100,255,0,0));
            incorrect++;

            if(one.getText().equals(question[language])){
                one.setBackgroundColor(Color.argb(100,0,255,0));
                //one.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }
            if(two.getText().equals(question[language])){
                two.setBackgroundColor(Color.argb(100,0,255,0));
                //two.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }
            if(three.getText().equals(question[language])){
                four.setBackgroundColor(Color.argb(100,0,255,0));
                //three.setBackgroundColor(Color.argb(100,255,0,0));
                //three.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }

            if(four.getText().equals(question[language])){
                four.setBackgroundColor(Color.argb(100,0,255,0));
                //four.setBackgroundColor(Color.argb(100,255,0,0));
                //four.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }
        }

        //Restore Changes

        Log.i("Vocab","Restore Done");
    }

    /**
     * Those methods ???????????
     */
    public void btn1(View view){
        checkForWinner(view, one);
    }
    public void btn2(View view){
        checkForWinner(view, two);
    }
    public void btn3(View view){
        checkForWinner(view, three);
    }
    public void btn4(View view){
        checkForWinner(view, four);
    }


    /**
     * This method creates the
     * @language gets assigned to language1 or language2 (different languages in each row)
     * @question splits the arraylist @allVocabs into language1, language2 and category
     * the if-loop ??????????
     * @wrongAnswers ??????????
     */
    public void chooseWord(){
        Button next = findViewById(R.id.next_button);
        next.setVisibility(Button.INVISIBLE);


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
        /**
         * The switch method declares which button holds the correct answer
         */

        switch (correctButton){
            case 0:
                one.setText(question[language]); //Richtige Lösung
                two.setText(wrongAnswers[0].split(";")[language]);
                three.setText(wrongAnswers[1].split(";")[language]);
                four.setText(wrongAnswers[2].split(";")[language]);
                break;
            case 1:
                one.setText(wrongAnswers[0].split(";")[language]);
                two.setText(question[language]); //Richtige Lösung
                three.setText(wrongAnswers[1].split(";")[language]);
                four.setText(wrongAnswers[2].split(";")[language]);
                break;
            case 2:
                one.setText(wrongAnswers[0].split(";")[language]);
                two.setText(wrongAnswers[1].split(";")[language]);
                three.setText(question[language]); //Richtige Lösung
                four.setText(wrongAnswers[2].split(";")[language]);
                break;
            case 3:
                one.setText(wrongAnswers[0].split(";")[language]);
                two.setText(wrongAnswers[1].split(";")[language]);
                three.setText(wrongAnswers[2].split(";")[language]);
                four.setText(question[language]); //Richtige Lösung
                break;
        }
        if(language == 0)
            vocable.setText(question[1]);
        else vocable.setText(question[0]);

    }

    /**
     * This method repeats the whole word choosing process with a new word
     */
    public void nextWord(View view){
        //Enable Buttons
        one.setClickable(true);
        two.setClickable(true);
        three.setClickable(true);
        four.setClickable(true);

        /*
        one.setBackground(getDrawable(R.drawable.button_style));
        two.setBackground(getDrawable(R.drawable.button_style));
        three.setBackground(getDrawable(R.drawable.button_style));
        four.setBackground(getDrawable(R.drawable.button_style));
*/
        allVocabs.add(wrongAnswers[0]);
        allVocabs.add(wrongAnswers[1]);
        allVocabs.add(wrongAnswers[2]);
        allVocabs.add(question[0]+";"+question[1]+";"+question[2]);

        one.setBackgroundColor(Color.argb(100,1,135,134));
        two.setBackgroundColor(Color.argb(100,1,135,134));
        three.setBackgroundColor(Color.argb(100,1,135,134));
        four.setBackgroundColor(Color.argb(100,1,135,134));
        numOfRounds++;
        if (counter == numOfRounds) {
            Log.i("Vocabs","Counter Zero");
            Log.i("Vocabs","num of Rounds " + numOfRounds);
            Log.i("Vocabs","+ "+ correct);
            Log.i("Vocabs","- "+ incorrect);

            Intent b = new Intent(ViewInformation.this, Results.class);
            b.putExtra("Correct",correct);
            b.putExtra("Incorrect",incorrect);
            b.putExtra("Runden", numOfRounds);
            b.putExtra("Counter", counter);

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

        }
        else{
            chooseWord();
        }


    }
}
/**
one.setHighlightColor(Color.argb(100,1,135,134));
one.setBackgroundColor(Color.argb(100,1,135,134));
one.getBackground().clearColorFilter();
one.setBackground(new Button(one.getContext()).getBackground());
one.setBackgroundResource(android.R.drawable.btn_default);

 one.setBackground(getDrawable(R.drawable.button_style));
 two.setBackground(getDrawable(R.drawable.button_style));
 three.setBackground(getDrawable(R.drawable.button_style));
 four.setBackground(getDrawable(R.drawable.button_style));

 one.getBackground().clearColorFilter();
 two.getBackground().clearColorFilter();
 three.getBackground().clearColorFilter();
 four.getBackground().clearColorFilter();


 */
