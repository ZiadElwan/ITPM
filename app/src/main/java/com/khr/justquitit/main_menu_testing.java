package com.khr.justquitit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class main_menu_testing extends AppCompatActivity {
    private Button btnLogout;
    Button btnFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_testing);

        btnFeedback = findViewById(R.id.btn_feedback_link);
        btnLogout = findViewById(R.id.btn_logout);

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_menu_testing.this, user_feedback_page.class);
                startActivity(intent);


            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(main_menu_testing.this, login_page.class));
            }
        });




    }


}