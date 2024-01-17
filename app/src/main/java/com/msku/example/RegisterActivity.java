package com.msku.example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.msku.example.rentcar.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import kotlin.io.TextStreamsKt;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
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
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        RegisterButtonClick();
    }

    private void RegisterButtonClick() {
        registerButton.setOnClickListener(view ->{
            String fullName = "";
            String email = "";
            String dateOfBirth = "";
            String phoneNumber = "";
            String city = "";
            String password = "";
            fullName = String.valueOf(fullNameEditText.getText());
            email = String.valueOf(emailEditText.getText());
            dateOfBirth = String.valueOf( dateOfBirthEditText.getText());
            phoneNumber = String.valueOf( phoneNumberEditText.getText());
            city = String.valueOf( cityEditText.getText());
            password = String.valueOf( passwordEditText.getText());
            if (TextUtils.isEmpty(fullName)) {
                Toast.makeText(RegisterActivity.this,"Enter Name",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(RegisterActivity.this,"Enter Email",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(dateOfBirth)) {
                Toast.makeText(RegisterActivity.this,"Enter Date of Birth",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(phoneNumber)) {
                Toast.makeText(RegisterActivity.this,"Enter Phone Number",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(city)) {
                Toast.makeText(RegisterActivity.this,"Enter City",Toast.LENGTH_SHORT).show();
                return;
            }
             if (TextUtils.isEmpty(password)) {
                Toast.makeText(RegisterActivity.this,"Enter Password",Toast.LENGTH_SHORT).show();
                return;
            }

            String finalFullName = fullName;
            String finalEmail = email;
            String finalDateOfBirth = dateOfBirth;
            String finalPhoneNumber = phoneNumber;
            String finalCity = city;
            String finalPassword = password;
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(RegisterActivity.this,"Account created.",Toast.LENGTH_SHORT).show();
                                ArrayList<Car> cars = new ArrayList<>();
                                User user = new User(finalFullName, finalEmail, finalDateOfBirth, finalPhoneNumber, finalCity, finalPassword,cars);
                                //UserManagement.users.put(user.email,user);
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                String userId = firebaseUser.getUid();
                                DatabaseReference reference = database.getReference("Users").child(userId);
                                reference.push().setValue(user);
                                Intent intent =new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(RegisterActivity.this,"Authentication failed.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        });
    }
}