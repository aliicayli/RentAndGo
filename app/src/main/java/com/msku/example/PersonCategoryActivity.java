package com.msku.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.msku.example.rentcar.R;

public class PersonCategoryActivity extends AppCompatActivity {
CardView carOwnerCardView;
CardView passengerCardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_category);
        carOwnerCardView = findViewById(R.id.carOwner);
        passengerCardView = findViewById(R.id.passenger);

    }


}