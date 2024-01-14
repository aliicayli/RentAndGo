package com.msku.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.msku.example.rentcar.R;

import java.text.DateFormat;
import java.util.Calendar;

public class InformationAdActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
Button continueBookingButton;
TextView pickUpDateTextView;
TextView returnDateTextView;
TextView activeDateTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_ad);
        continueBookingButton = findViewById(R.id.continueBooking);
        pickUpDateTextView = findViewById(R.id.pickupDate);
        returnDateTextView= findViewById(R.id.returnDate);
        ContinueBookingButtonClick();
        PickUpDateTextViewClick();
        ReturnDateTextViewClick();
    }

    private void ContinueBookingButtonClick() {
        continueBookingButton.setOnClickListener(view ->{
            startActivity(new Intent(this,BookingCompleteActivity.class));
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