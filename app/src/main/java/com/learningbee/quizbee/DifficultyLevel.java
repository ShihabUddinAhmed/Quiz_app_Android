package com.learningbee.quizbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DifficultyLevel extends AppCompatActivity {
    private TextView qText;
    private RadioGroup radioGroup;
    private RadioButton rbHard;
    private RadioButton rbMedium;
    private RadioButton rbEasy;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_level);

        qText = findViewById(R.id.textView5);
        radioGroup = findViewById(R.id.difficultyLevel);
        rbHard = findViewById(R.id.radioButton);
        rbMedium = findViewById(R.id.radioButton2);
        rbEasy = findViewById(R.id.radioButton3);
        startButton = findViewById(R.id.button2);
        radioGroup.clearCheck();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbHard.isChecked() || rbMedium.isChecked() || rbEasy.isChecked()){
                    RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
                    int ansLevel = radioGroup.indexOfChild(rbSelected);
                    Intent intent = new Intent(DifficultyLevel.this, questionPage.class);
                    intent.putExtra("difficultyLevel",ansLevel);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(DifficultyLevel.this, "Select a level!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}