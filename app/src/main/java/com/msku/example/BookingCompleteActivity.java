package com.msku.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.msku.example.rentcar.R;

public class BookingCompleteActivity extends AppCompatActivity {
public TextView nameTextView;
public TextView emailTextView;
public TextView phoneNumberTextView;
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_complete_activity);
        intent =getIntent();
        nameTextView = findViewById(R.id.name);
        emailTextView = findViewById(R.id.email);
        phoneNumberTextView = findViewById(R.id.phoneNumber);
        GetPersonalData();
    }

    void GetPersonalData(){
        nameTextView.setText(intent.getStringExtra("firstName"));
        emailTextView.setText(intent.getStringExtra("lastName"));
        phoneNumberTextView.setText(intent.getStringExtra("phoneNumber"));
    }
}