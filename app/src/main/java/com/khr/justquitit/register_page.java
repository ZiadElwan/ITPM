package com.khr.justquitit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class register_page extends AppCompatActivity {
    Button btnRegister;
    EditText mName, mPassword, mRetype, mEmail, mAge, mStartDate, mEndDate;
    TextView backLogin;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseUser fUser;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatePickerDialog.OnDateSetListener setListener;
    DatePickerDialog.OnDateSetListener setListener2;

    String name, email, age, startDate, endDate, password;

    ArrayAdapter<String> adapter;

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
        mAge = findViewById(R.id.age_rgt);
        mStartDate = findViewById(R.id.startdate_rgt);
        mEndDate = findViewById(R.id.enddate_rgt);

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

        /*reference = FirebaseDatabase.getInstance().getReference("UserData");*/


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        mStartDate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        register_page.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener, year, month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+ year;
                mStartDate.setText(date);
            }
        };

        mEndDate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        register_page.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener2, year, month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        setListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+ year;
                mEndDate.setText(date);
            }
        };



       /* mStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        register_page.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        mStartDate.setText(date);
                    }

                    }, year, month, day);
                datePickerDialog.show();
            }
        }); */



        btnRegister = findViewById(R.id.signup_btn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();

               /*insertUserData();*/


            }

            /*private void insertUserData() {
                String name = mName.getText().toString();
                String password = mPassword.getText().toString();
                String email = mEmail.getText().toString();
                String age = mAge.getText().toString();
                String startDate = mStartDate.getText().toString();
                String endDate = mEndDate.getText().toString();

                UserData UserData = new UserData(name, password, email, age, startDate, endDate);
                reference.push().setValue(UserData);
            }*/
        });
    }
            private void PerforAuth() {
                String name = mName.getText().toString();
                String password = mPassword.getText().toString();
                String retype = mRetype.getText().toString();
                String email = mEmail.getText().toString();
                String age = mAge.getText().toString();
                String startDate = mStartDate.getText().toString();
                String endDate = mEndDate.getText().toString();

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

                else if(!password.equals(retype)){
                    mRetype.setError("Password not matched!");
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



                else if(age.isEmpty()){
                    mAge.setError("Age is required!");
                    mAge.requestFocus();
                }

                else if(startDate.isEmpty()){
                    mStartDate.setError("Start recovery date is required!");
                    mStartDate.requestFocus();
                }

                else if(endDate.isEmpty()){
                    mEndDate.setError("Expected success date is required!");
                    mEndDate.requestFocus();
                }

                else{
                    progressBar.setVisibility(View.VISIBLE);
                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendUserData();
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

    private void sendUserData(){
        /*FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();*/
        reference = FirebaseDatabase.getInstance().getReference(fAuth.getUid());

        String name = mName.getText().toString();
        String password = mPassword.getText().toString();
        String email = mEmail.getText().toString();
        String age = mAge.getText().toString();
        String startDate = mStartDate.getText().toString();
        String endDate = mEndDate.getText().toString();

        UserData UserData = new UserData(name, password, email, age, startDate, endDate);
        reference.setValue(UserData);
    }

}
