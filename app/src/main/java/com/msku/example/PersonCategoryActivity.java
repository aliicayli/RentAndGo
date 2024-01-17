package com.msku.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.msku.example.rentcar.R;

public class PersonCategoryActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
CardView carOwnerCardView;
CardView passengerCardView;
Button logOutButton;
Button myCars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_category);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        carOwnerCardView = findViewById(R.id.carOwner);
        passengerCardView = findViewById(R.id.passenger);
        logOutButton = findViewById(R.id.logOutButton);
        myCars = findViewById(R.id.buttonMyCars);
        CarOwnerCardViewClick();
        PassengerCardViewClick();
        LogOutButtonClick();
        handleMyCarsButtonClick();
    }

    private void handleMyCarsButtonClick() {
        myCars.setOnClickListener(view->{
            startActivity(new Intent(this, OwnersCar.class));
        });
    }

    private void CarOwnerCardViewClick() {
        carOwnerCardView.setOnClickListener(view ->{
            startActivity(new Intent(this,AddVehicleActivity.class));
        });
    }

    private void PassengerCardViewClick() {
        passengerCardView.setOnClickListener(view ->{
            startActivity(new Intent(this,CarCategoryActivity.class));
        });
    }

    private void LogOutButtonClick() {
        logOutButton.setOnClickListener(view ->{
        FirebaseAuth.getInstance().signOut();
            Intent intent =new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}