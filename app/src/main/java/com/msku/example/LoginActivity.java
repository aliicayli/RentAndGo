package com.msku.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.msku.example.rentcar.R;

public class LoginActivity extends AppCompatActivity {
    TextView registerTextView;
    TextView emailTextView;
    TextView passwordTextView;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerTextView = findViewById(R.id.register);
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        RegisterTextViewClick();
        LoginButtonClick();
    }
    private void RegisterTextViewClick() {
        registerTextView.setOnClickListener(view ->{
            startActivity(new Intent(this,RegisterActivity.class));
        });
    }

    private void LoginButtonClick() {
        loginButton.setOnClickListener(view ->{
            String emailText = emailTextView.getText().toString();
            String passwordText = passwordTextView.getText().toString();
            String loginQuestText = UserManagement.users.get(emailText).password;
            if (loginQuestText.equals(passwordText)) {
                UserManagement.loggedEmail = emailText;
                startActivity(new Intent(this,PersonCategoryActivity.class));
            }
        });
    }

}