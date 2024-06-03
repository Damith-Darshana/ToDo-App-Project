package com.hfad.todoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class loginSignupScreenActivity extends AppCompatActivity {

    Button loginButton,signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup_screen);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        loginButton = findViewById(R.id.login_btn_main);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(loginSignupScreenActivity.this,loginScreenActivity.class);
                Bundle i = ActivityOptions.makeSceneTransitionAnimation(loginSignupScreenActivity.this).toBundle();
                startActivity(newIntent,i);



            }
        });

        signupButton = findViewById(R.id.signup_btn_main);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginSignupScreenActivity.this,SignupScreenActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}