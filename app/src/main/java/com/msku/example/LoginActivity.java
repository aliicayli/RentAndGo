package com.msku.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.msku.example.rentcar.R;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth auth;
    TextView registerTextView;
    TextView emailTextView;
    TextView passwordTextView;
    Button loginButton;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent intent =new Intent(getApplicationContext(),PersonCategoryActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerTextView = findViewById(R.id.register);
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        auth = FirebaseAuth.getInstance();
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
            String email = "";
            String password = "";

            email = String.valueOf(emailTextView.getText());
            password = String.valueOf(passwordTextView.getText());

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(LoginActivity.this,"Enter Email",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this,"Enter Password",Toast.LENGTH_SHORT).show();
                return;
            }

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login Successful",Toast.LENGTH_SHORT).show();
                                Intent intent =new Intent(getApplicationContext(),PersonCategoryActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Login failed.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }

}