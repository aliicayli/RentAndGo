package com.msku.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.Firebase;
import com.msku.example.rentcar.R;

import java.text.DateFormat;
import java.util.Calendar;

public class InformationAdActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{//yok. tamam listeeki suv a tıklyaınca range rover geliyor. Range ye tıklayınca info panel geliyor.. info burası demievet
Button continueBookingButton;
TextView pickUpDateTextView;
TextView returnDateTextView;
TextView activeDateTextView;
TextView firstNameTextView;
TextView lastNameTextView;
TextView emailTextView;
TextView phoneNumberTextView;

Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_ad);

        intent = getIntent();


        continueBookingButton = findViewById(R.id.continueBooking);
        pickUpDateTextView = findViewById(R.id.pickupDate);
        returnDateTextView= findViewById(R.id.returnDate);
        firstNameTextView = findViewById(R.id.firstName);
        lastNameTextView = findViewById(R.id.lastName);
        emailTextView = findViewById(R.id.email);
        phoneNumberTextView = findViewById(R.id.phoneNumber);
        ContinueBookingButtonClick();
        PickUpDateTextViewClick();
        ReturnDateTextViewClick();
    }

    private void ContinueBookingButtonClick() {
        continueBookingButton.setOnClickListener(view ->{
            Intent intentforComplete = new Intent(this,BookingCompleteActivity.class);
            intentforComplete.putExtra("firstName", intent.getStringExtra("firstName"));
            intentforComplete.putExtra("lastName", intent.getStringExtra("lastName"));
            intentforComplete.putExtra("phoneNumber", intent.getStringExtra("phoneNumber"));
            //UserManagement.ads.get(UserManagement.selectedAdID).firstName  = firstNameTextView.getText().toString();
            //UserManagement.ads.get(UserManagement.selectedAdID).lastName   = lastNameTextView.getText().toString();
            //UserManagement.ads.get(UserManagement.selectedAdID).email      = emailTextView.getText().toString();
            //UserManagement.ads.get(UserManagement.selectedAdID).phoneNumber = phoneNumberTextView.getText().toString();
            startActivity(intentforComplete);
        });
    }
    private void PickUpDateTextViewClick() {
        pickUpDateTextView.setOnClickListener(view ->{
            activeDateTextView = pickUpDateTextView;
            DialogFragment dataPicker = new DatePickerFragment();
            dataPicker.show(getSupportFragmentManager(),"Date Picker");
        });
    }

    private void ReturnDateTextViewClick() {
        returnDateTextView.setOnClickListener(view ->{
            activeDateTextView = returnDateTextView;
            DialogFragment dataPicker = new DatePickerFragment();
            dataPicker.show(getSupportFragmentManager(),"Date Picker");
        });
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        activeDateTextView.setText(currentDateString.toString());
    }

}