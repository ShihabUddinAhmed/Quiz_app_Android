package com.learningbee.quizbee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ScorePage extends AppCompatActivity {
    private TextView textViewScore;
    private TextView textViewHighScore;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);

        textViewScore = findViewById(R.id.textView8);
        int score = getIntent().getIntExtra("Score",0);
        textViewScore.setText("Score: " + score);
        textViewHighScore = findViewById(R.id.textView2);
        imageButton = findViewById(R.id.imageButton);
        Score scoreObj = new Score(score);

        QuizDBHelper qdbhelper = new QuizDBHelper(this);
        try{
            qdbhelper.CheckDatabaseExistance();
        }catch (Exception e){e.printStackTrace();}
        try {
            qdbhelper.OpenDatabase();
        }catch (Exception e){e.printStackTrace();}

        qdbhelper.addScores(scoreObj);
        int highScore = qdbhelper.getHighScore();

        textViewHighScore.setText("High Score: "+ highScore);
        qdbhelper.close();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}