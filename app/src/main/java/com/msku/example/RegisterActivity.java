package com.msku.example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.msku.example.rentcar.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RegisterActivity extends AppCompatActivity {
    EditText fullNameEditText;
    EditText emailEditText;
    EditText dateOfBirthEditText;
    EditText phoneNumberEditText;
    EditText cityEditText;
    EditText passwordEditText;
    Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton = findViewById(R.id.register);
        fullNameEditText = findViewById(R.id.firstName);
        emailEditText = findViewById(R.id.email);
        dateOfBirthEditText = findViewById(R.id.dob);
        phoneNumberEditText = findViewById(R.id.phoneNumber);
        cityEditText = findViewById(R.id.city);
        passwordEditText = findViewById(R.id.password);
        RegisterButtonClick();
    }

    private void RegisterButtonClick() {
        registerButton.setOnClickListener(view ->{
            String fullName = "a";
            String email = "";
            String dateOfBirth = "";
            String phoneNumber = "";
            String city = "";
            String password = "";
            if (fullNameEditText.getText() == null) fullName = "";
            else fullName = fullNameEditText.getText().toString();

            if (emailEditText.getText() == null) email = "";
            else email = emailEditText.getText().toString();

            if (dateOfBirthEditText.getText() == null) dateOfBirth = "";
            else dateOfBirth = dateOfBirthEditText.getText().toString();

            if (phoneNumberEditText.getText() == null) phoneNumber = "";
            else phoneNumber = phoneNumberEditText.getText().toString();

            if (cityEditText.getText() == null) city = "";
            else city = cityEditText.getText().toString();

            if (passwordEditText.getText() == null) password = "";
            else password = passwordEditText.getText().toString();

            User user = new User(fullName,email,dateOfBirth,phoneNumber,city,password);
            //ArrayList<String> data = new ArrayList<>() ;
            //data.add(user.fullName);
            //data.add(user.email);
            //data.add(user.dateOfBirth);
            //data.add(user.phoneNumber);
            //data.add(user.city);
            //data.add(user.password);
            UserManagement.users.put(user.email,user);

            startActivity(new Intent(this,LoginActivity.class));
        });
    }
}