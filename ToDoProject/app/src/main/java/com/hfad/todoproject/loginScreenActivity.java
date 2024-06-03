package com.hfad.todoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginScreenActivity extends AppCompatActivity {

    EditText userEmailLoginEditText,passwordLoginEditText;
    TextView signupText;
    Button loginScreenButton ;
    SharedPreferencesManager sharedPreferencesManager;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        userEmailLoginEditText = findViewById(R.id.loginscreen_email_edittext);
        passwordLoginEditText = findViewById(R.id.loginscreen_password_edittext);
        loginScreenButton  = findViewById(R.id.loginscreen_login_button);
        signupText = findViewById(R.id.loginscreen_signup_text);

        sharedPreferencesManager = new SharedPreferencesManager(this);

        loginScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmailLoginEditText.getText().toString();
                String password = passwordLoginEditText.getText().toString();

                 UserCredentials userData = sharedPreferencesManager.getUserData();
                 if (userData != null && userData.getEmail().equals(email) && userData.getPassword().equals(password)){


                     Toast.makeText(loginScreenActivity.this, "login Successful", Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(loginScreenActivity.this,MainActivity.class);
                     startActivity(intent);
                     finish();

                 }
                 else{
                     Toast.makeText(loginScreenActivity.this, "login Fail", Toast.LENGTH_SHORT).show();
                 }

            }
        });

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginScreenActivity.this,SignupScreenActivity.class);
                startActivity(intent);

            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}