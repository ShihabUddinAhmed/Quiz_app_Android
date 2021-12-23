package com.learningbee.quizbee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class questionPage extends AppCompatActivity {
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewHint;
    //private TextView textViewFoot;
    private RadioGroup rbgroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirm;

    private ColorStateList textColorRbGroup;

    private List<Question> questionList;

    private int questionCounter;
    private int questionCountTotal;
    private Question currentQ;

    private int score = 0;
    private boolean answered;
    private int level;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;
    private TextView textViewOnDialog;
    private Button popupOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);

        textViewQuestion = findViewById(R.id.textView6);
        textViewScore = findViewById(R.id.textView9);
        textViewHint = findViewById(R.id.textView7);
        textViewHint.setText(" ");
        //textViewFoot = findViewById(R.id.textView10);
        //textViewFoot.setText(" ");
        rbgroup = findViewById(R.id.ansOptions);
        rb1 = findViewById(R.id.radioButton4);
        rb2 = findViewById(R.id.radioButton5);
        rb3 = findViewById(R.id.radioButton6);
        rb4 = findViewById(R.id.radioButton7);
        buttonConfirm = findViewById(R.id.button3);
        level = getIntent().getIntExtra("difficultyLevel",-1);

        textColorRbGroup = rb1.getTextColors();

        QuizDBHelper qdbhelper = new QuizDBHelper(this);
        try{
            qdbhelper.CheckDatabaseExistance();
        }catch (Exception e){e.printStackTrace();}
        try {
            qdbhelper.OpenDatabase();
        }catch (Exception e){e.printStackTrace();}

        if(level == 0){
            questionList = qdbhelper.getAllHardQuestions();
        }
        else if(level == 1){
            questionList = qdbhelper.getAllMediumQuestions();
        }
        else if(level == 2){
            questionList = qdbhelper.getAllEasyQuestions();
        }
        qdbhelper.close();

        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered){
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        checkAnswer();
                    }
                    else {
                        Toast.makeText(questionPage.this, "Select an answer first!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    showNextQuestion();
                }
            }
        });
    }

    private void checkAnswer(){
        answered = true;

        RadioButton rbSelected = findViewById(rbgroup.getCheckedRadioButtonId());
        int ansNR = rbgroup.indexOfChild(rbSelected) + 1;

        if (ansNR == currentQ.getCorrectOption()){
            score++;
            textViewScore.setText("Score: " + score);
        }
        showSolution();
    }

    private void showSolutionPopUp(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View solutionPopUp = getLayoutInflater().inflate(R.layout.showsolutionpopup, null);
        textViewOnDialog = (TextView) solutionPopUp.findViewById(R.id.solutionText);
        popupOK = (Button) solutionPopUp.findViewById(R.id.gotitbutton);

        dialogBuilder.setView(solutionPopUp);
        alertDialog = dialogBuilder.create();
        alertDialog.show();

        textViewOnDialog.setText(currentQ.getAnswerFootnote());
        popupOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //defining the dismiss
                alertDialog.dismiss();
            }
        });
    }

    private void showSolution(){
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);
        //textViewFoot.setText(currentQ.getAnswerFootnote());
        showSolutionPopUp();
        switch (currentQ.getCorrectOption()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                break;
        }
        if (questionCounter < 10 && questionCounter < questionCountTotal){
            buttonConfirm.setText("Next");
        }
        else {
            buttonConfirm.setText("Submit");
        }
    }

    private void showNextQuestion(){
        rb1.setTextColor(textColorRbGroup);
        rb2.setTextColor(textColorRbGroup);
        rb3.setTextColor(textColorRbGroup);
        rb4.setTextColor(textColorRbGroup);
        rbgroup.clearCheck();
        //textViewFoot.setText(" ");

        if (questionCounter < 10 && questionCounter < questionCountTotal){
            currentQ = questionList.get(questionCounter);
            int currQN = questionCounter + 1;
            textViewQuestion.setText(currQN + ". "+currentQ.getText());
            textViewHint.setText("Hint: "+currentQ.getHint());
            rb1.setText(currentQ.getOption1());
            rb2.setText(currentQ.getOption2());
            rb3.setText(currentQ.getOption3());
            rb4.setText(currentQ.getOption4());
            questionCounter++;
            answered = false;
            buttonConfirm.setText("Confirm");
        }
        else {
            finishQuiz();
        }
    }

    private void finishQuiz(){
        Intent intent = new Intent(questionPage.this, ScorePage.class);
        intent.putExtra("Score",score);
        startActivity(intent);
        finish();
    }
}