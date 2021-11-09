package com.khr.justquitit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class user_feedback_page extends AppCompatActivity {
    Button btnSubmitFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        setContentView(R.layout.user_feedback_page);

        btnSubmitFeedback = findViewById(R.id.btn_submit_feedback);
        final EditText mEmail = findViewById(R.id.text_email_feedback);
        final EditText mFeedback = findViewById(R.id.text_feedback);
        DAOfeedback dao = new DAOfeedback();
        btnSubmitFeedback.setOnClickListener(v->
        {
            FeedbackData feed = new FeedbackData(mEmail.getText().toString(), mFeedback.getText().toString());
            dao.add(feed).addOnSuccessListener(suc->{
                Toast.makeText(this, "Feedback has been delivered", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
    }
}