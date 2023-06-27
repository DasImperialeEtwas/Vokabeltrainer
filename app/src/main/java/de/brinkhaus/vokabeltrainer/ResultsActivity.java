package de.brinkhaus.vokabeltrainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
/*
 * Results activity of the app.
 */

public class ResultsActivity extends AppCompatActivity {
    /*
     * This method is called when the activity is first created.
     */
    int numOfRounds;
    ArrayList<String> allVocabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        final ProgressBar pbRight = findViewById(R.id.progressBar_right);
        final ProgressBar pbFalse = findViewById(R.id.progressBar_false);
        final ProgressBar pBAll = findViewById(R.id.progressBar_all);

        Intent a = getIntent();
        int correct = a.getExtras().getInt("Correct");
        int incorrect = a.getExtras().getInt("Incorrect");
        int counter = a.getExtras().getInt("Counter");
        numOfRounds = a.getExtras().getInt("Runden");
        allVocabs = a.getExtras().getStringArrayList("allVocabs");

        pbFalse.setProgress(0);
        pbRight.setProgress(0);
        pBAll.setProgress(0);

        pbRight.setMax(counter);
        pbFalse.setMax(counter);
        pBAll.setMax(allVocabs.size());

        pbFalse.setProgress(incorrect);
        pbRight.setProgress(correct);
        int zahl = allVocabs.size();
        int ball = zahl- counter;
        pBAll.setProgress(ball);

    }
    public void newRound(View view){
        Intent c = new Intent(ResultsActivity.this, TrainingActivity.class);
        startActivity(c);
        numOfRounds++;
    }

    public void backStart(View view){
        Intent d = new Intent(ResultsActivity.this, MainActivity.class);
        startActivity(d);
    }
}
