package com.msku.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.msku.example.rentcar.R;

public class InformationAdActivity extends AppCompatActivity {
Button continueBookingButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_ad);
        continueBookingButton = findViewById(R.id.continueBooking);
        ContinueBookingButtonClick();
    }

    private void ContinueBookingButtonClick() {
        continueBookingButton.setOnClickListener(view ->{
            startActivity(new Intent(this,BookingCompleteActivity.class));
        });
    }}