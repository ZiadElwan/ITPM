package com.khr.justquitit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import java.util.Random;

public class Survey extends AppCompatActivity {

    Button btnquit;
    private TextView questiontv, questionnumbertv;
    private Button option1btn, option2btn, option3btn, option4btn;
    private ArrayList<SurveyModal>surveyModalArrayList;
    Random random;
    int currentScore = 0, questionAttempted = 1, currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        btnquit = findViewById(R.id.btn_quit);

        btnquit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Survey.this, main_menu.class);
                startActivity(intent);
            }
        });

        questiontv = findViewById(R.id.question);
        questionnumbertv = findViewById(R.id.tv_questionsattempted);
        option1btn = findViewById(R.id.choice1);
        option2btn = findViewById(R.id.choice2);
        option3btn = findViewById(R.id.choice3);
        option4btn = findViewById(R.id.choice4);
        surveyModalArrayList = new ArrayList<>();
        random = new Random();
        getSurveyQuestion(surveyModalArrayList);
        currentPos = random.nextInt(surveyModalArrayList.size());
        setDataToViews(currentPos);

        option1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surveyModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option1btn.getText().toString().trim().toLowerCase())) {
                currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(surveyModalArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surveyModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option2btn.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(surveyModalArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surveyModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option3btn.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(surveyModalArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option4btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surveyModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option4btn.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(surveyModalArrayList.size());
                setDataToViews(currentPos);
            }
        });
    }

    private void setDataToViews(int currentPos){
        questionnumbertv.setText("Questions Attempted : " + questionAttempted + "/6");
        questiontv.setText(surveyModalArrayList.get(currentPos).getQuestion());
        option1btn.setText(surveyModalArrayList.get(currentPos).getOption1());
        option2btn.setText(surveyModalArrayList.get(currentPos).getOption2());
        option3btn.setText(surveyModalArrayList.get(currentPos).getOption3());
        option4btn.setText(surveyModalArrayList.get(currentPos).getOption4());

    }
    private void getSurveyQuestion(ArrayList<SurveyModal>surveyModalArrayList) {
        surveyModalArrayList.add(new SurveyModal("How soon after waking do you smoke your first cigarette?", "Within 5 minutes", "5-30 minutes", "31-60 minutes", ">60 minutes", "Within 5 minutes"));
        surveyModalArrayList.add(new SurveyModal("Do you find it difficult to refrain from smoking in places where it is forbidden? e.g. Library", "Strongly agree", "Agree", "Disagree", "Strongly Disagree", "Strongly agree"));
        surveyModalArrayList.add(new SurveyModal("Which cigarette would you hate to give up?", "The first in the morning", "After having a meal", "Any other", "I don't know", "The first in the morning"));
        surveyModalArrayList.add(new SurveyModal("How many cigarettes a day do you smoke?", "10 or less", "11-20", "21-30", "31 or more", "31 or more"));
        surveyModalArrayList.add(new SurveyModal("Do you smoke more frequently in the morning?", "Yes", "Rarely", "No", "I don't know", "Yes"));
        surveyModalArrayList.add(new SurveyModal("Do you smoke even if you are sick in bed most of the day?", "Yes", "Rarely", "No", "I don't know", "Yes"));
    }
}
