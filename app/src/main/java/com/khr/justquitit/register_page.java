package com.khr.justquitit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class register_page extends AppCompatActivity {
    Button btnRegister;
    EditText mName, mPassword, mRetype, mEmail;
    TextView backLogin;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        setContentView(R.layout.register_page);

        mName = findViewById(R.id.name_rgt);
        mPassword = findViewById(R.id.password_rgt);
        mRetype = findViewById(R.id.retypepassword_rgt);
        mEmail = findViewById(R.id.email_rgt);

        backLogin = findViewById(R.id.backLogin);
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register_page.this, login_page.class));
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();

        btnRegister = findViewById(R.id.signup_btn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();
            }
        });
    }
            private void PerforAuth() {
                String name = mName.getText().toString();
                String password = mPassword.getText().toString();
                String retype = mRetype.getText().toString();
                String email = mEmail.getText().toString();

                if(name.isEmpty()){
                    mName.setError("Username is required!");
                    mName.requestFocus();
                }

                else if(password.isEmpty()){
                    mPassword.setError("Password is required!");
                    mPassword.requestFocus();
                }

                else if(password.length()<6){
                    mPassword.setError("Password must be more than 6 characters!");
                    mPassword.requestFocus();
                }

                else if(retype.isEmpty()){
                    mRetype.setError("Re-type password is required!");
                    mRetype.requestFocus();
                }

                else if(email.isEmpty()){
                    mEmail.setError("Email is required!");
                    mEmail.requestFocus();
                }

                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    mEmail.setError("Please provide valid email!");
                    mEmail.requestFocus();
                }

                else if(!password.equals(retype)){
                    mRetype.setError("Password not matched!");

                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendUserToNextActivity();
                                Toast.makeText(register_page.this, "You have been registered successfully!",
                                        Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                            else{
                                Toast.makeText(register_page.this, "Failed to register! Try again!",
                                        Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }

                        private void sendUserToNextActivity() {
                            Intent intent = new Intent(register_page.this, login_page.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });

            }




    }



}
