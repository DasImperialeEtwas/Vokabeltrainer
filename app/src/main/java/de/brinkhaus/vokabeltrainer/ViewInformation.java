package de.brinkhaus.vokabeltrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
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

/*
 * Main activity of the app.
 */

public class ViewInformation extends AppCompatActivity {

    /*
     * This method is called when the activity is first created.
     * */
    private final int counter = 6;
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

        one = (Button) findViewById(R.id.answer_1);
        two = (Button) findViewById(R.id.answer_2);
        three = (Button) findViewById(R.id.answer_3);
        four = (Button) findViewById(R.id.answer_4);

        vocable = (TextView) findViewById(R.id.vocabel);
        instruction = (TextView) findViewById(R.id.anweisung);
        chooseWord();
    }

    //Color variable gets declared
    public static final int TEAL = (Color.parseColor("#ff018786"));

    /*
     * This method figure out which button holds the correct answer
     * Buttons get disabled to ensure that the answer can´t be changed
     */
    public void checkForWinner(Button btn){
        one.setClickable(false);
        two.setClickable(false);
        three.setClickable(false);
        four.setClickable(false);

        Button next = findViewById(R.id.next_button);
        next.setVisibility(Button.VISIBLE);

        /*
         * This if-loop checks if the correct answer was choosen
         * Buttons change color to "green" if the answer is correct
         * Buttons change color to "red" if the answer is wrong
         */
        if (btn.getText().equals(question[language])){
            //True
            btn.setBackgroundColor(Color.GREEN);
            question[2] = String.valueOf(Integer.parseInt(question[2]) +1);
            correct++;
        }
        else{
            //Wrong
            question[2] = "0";
            btn.setBackgroundColor(Color.RED);
            incorrect++;

            if(one.getText().equals(question[language])){
                one.setBackgroundColor(Color.GREEN);
            }
            if(two.getText().equals(question[language])){
                two.setBackgroundColor(Color.GREEN);
            }
            if(three.getText().equals(question[language])){
                four.setBackgroundColor(Color.GREEN);
            }

            if(four.getText().equals(question[language])){
                four.setBackgroundColor(Color.GREEN);
            }
        }
    }

    /*
     * Those methods take action when one of the buttons is pressed. They start the checking process of the answers
     */
    public void btn1(View view){
        checkForWinner(one);
    }
    public void btn2(View view){
        checkForWinner(two);
    }
    public void btn3(View view){
        checkForWinner(three);
    }
    public void btn4(View view){
        checkForWinner(four);
    }


    /*
     * This method creates the
     * language gets assigned to language1 or language2 (different languages in each row)
     * question splits the arraylist @allVocabs into language1, language2 and category
     * the if-loop ??????????
     * wrongAnswers ??????????
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

        int correctButton = random.nextInt(4);
        /*
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
        if(language == 0) {
            vocable.setText(question[1]);
        } else vocable.setText(question[0]);
    }

    /*
     * This method repeats the whole word choosing process with a new word
     * onClick listener for the next button
     */
    public void nextWord(View view){
        //Enable Buttons
        one.setClickable(true);
        two.setClickable(true);
        three.setClickable(true);
        four.setClickable(true);

        allVocabs.add(wrongAnswers[0]);
        allVocabs.add(wrongAnswers[1]);
        allVocabs.add(wrongAnswers[2]);
        allVocabs.add(question[0]+";"+question[1]+";"+question[2]);

        one.setBackgroundColor(TEAL);
        two.setBackgroundColor(TEAL);
        three.setBackgroundColor(TEAL);
        four.setBackgroundColor(TEAL);

        numOfRounds++;
        if (counter == numOfRounds) {
            Intent b = new Intent(ViewInformation.this, Results.class);
            b.putExtra("Correct",correct);
            b.putExtra("Incorrect",incorrect);
            b.putExtra("Runden", numOfRounds);
            b.putExtra("Counter", counter);
            b.putExtra("allVocabs", allVocabs);

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
        else
        {
            chooseWord();
        }
    }
}