package com.learningbee.quizbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textViewHighScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_home_page);

        textViewHighScore = findViewById(R.id.textView);

        QuizDBHelper qdbhelper = new QuizDBHelper(this);
        try{
            qdbhelper.CheckDatabaseExistance();
        }catch (Exception e){e.printStackTrace();}
        try {
            qdbhelper.OpenDatabase();
        }catch (Exception e){e.printStackTrace();}

        int highScore = qdbhelper.getHighScore();

        textViewHighScore.setText("High Score: "+ highScore);
        qdbhelper.close();

        Button buttonStartQuiz = findViewById(R.id.button);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }
    private void startQuiz()
    {
        Intent intent = new Intent(MainActivity.this, DifficultyLevel.class);
        startActivity(intent);
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        QuizDBHelper qdbhelper = new QuizDBHelper(this);
        try{
            qdbhelper.CheckDatabaseExistance();
        }catch (Exception e){e.printStackTrace();}
        try {
            qdbhelper.OpenDatabase();
        }catch (Exception e){e.printStackTrace();}

        int highScore = qdbhelper.getHighScore();

        textViewHighScore.setText("High Score: "+ highScore);
        qdbhelper.close();
    }
}