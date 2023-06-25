package de.brinkhaus.vokabeltrainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class Results extends AppCompatActivity {

    private int correct = 0;
    private int incorrect = 0;
    private int counter = 3;
    private int numOfRounds = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        final ProgressBar pbRight = (ProgressBar) findViewById(R.id.progressBar_right);
        final ProgressBar pbFalse = (ProgressBar) findViewById(R.id.progressBar_false);
        final ProgressBar pBAll = (ProgressBar) findViewById(R.id.progressBar_all);

        Intent a = getIntent();
        correct = a.getExtras().getInt("Correct");
        incorrect = a.getExtras().getInt("Incorrect");
        counter = a.getExtras().getInt("Counter");
        numOfRounds = a.getExtras().getInt("Runden");

        pbFalse.setProgress(0);
        pbRight.setProgress(0);

        pbRight.setMax(counter);
        pbFalse.setMax(counter);
        //pBAll.setMax()

        pbFalse.setProgress(incorrect);
        pbRight.setProgress(correct);


    }
    public void newRound(View view){
        Intent c = new Intent(Results.this, ViewInformation.class);
        startActivity(c);
        numOfRounds++;

        //wert von correct muss übernommen und mit neuem correct wert addiert werden
    }

    public void backStart(View view){
        Intent d = new Intent(Results.this, MainActivity.class);
        d.putExtra("Correct",correct);
        d.putExtra("Incorrect",incorrect);
        d.putExtra("Runden", numOfRounds);
        d.putExtra("Counter", counter);
        startActivity(d);
    }
}
