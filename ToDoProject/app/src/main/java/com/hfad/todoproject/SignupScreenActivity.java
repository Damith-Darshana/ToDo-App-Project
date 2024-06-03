package com.hfad.todoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupScreenActivity extends AppCompatActivity {

    SharedPreferencesManager sharedPreferencesManager;
    EditText userNameEditText, passwordEditText,emailEditText,confirmPasswordEditText;
    TextView loginText;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);


        userNameEditText = findViewById(R.id.signup_name_edittext);
        passwordEditText = findViewById(R.id.signup_password_edittext);
        emailEditText = findViewById(R.id.signup_email_edittext);
        loginText = findViewById(R.id.signupscreen_login_button_text);
        confirmPasswordEditText = findViewById(R.id.signup_confirm_password_edittext);
        sharedPreferencesManager = new SharedPreferencesManager(this);

        signUpButton = findViewById(R.id.signup_button_signscreen);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confPwd= confirmPasswordEditText.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(SignupScreenActivity.this, "Name Cannot be Empty", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {
                    Toast.makeText(SignupScreenActivity.this, "Email Cannot be Empty", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(SignupScreenActivity.this, "Password is Required ", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confPwd)) {

                    Toast.makeText(SignupScreenActivity.this, "password and Confirm Password should be same", Toast.LENGTH_SHORT).show();
                } else {
                    sharedPreferencesManager.saveUserData(name, password, email);
                    Intent intent = new Intent(SignupScreenActivity.this, loginScreenActivity.class);
                    Toast.makeText(SignupScreenActivity.this, "Account created Successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                }


            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupScreenActivity.this,loginScreenActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}