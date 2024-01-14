package com.msku.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.msku.example.rentcar.R;

public class BookingCompleteActivity extends AppCompatActivity {
public TextView nameTextView;
public TextView emailTextView;
public TextView phoneNumberTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_complete_activity);
        nameTextView = findViewById(R.id.name);
        emailTextView = findViewById(R.id.email);
        phoneNumberTextView = findViewById(R.id.phoneNumber);
        GetPersonalData();
    }

    void GetPersonalData(){
        nameTextView.setText(UserManagement.ads.get(UserManagement.selectedCategory).firstName);
        emailTextView.setText(UserManagement.ads.get(UserManagement.selectedCategory).email);
        phoneNumberTextView.setText(UserManagement.ads.get(UserManagement.selectedCategory).phoneNumber);
    }
}