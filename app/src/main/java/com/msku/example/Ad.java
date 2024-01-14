package com.msku.example;

import java.util.Date;

public class Ad {
    public  int id;
    public Car car;
    public Date pickupDate;
    public Date returnDate;
    public  String firstName;
    public  String lastName;
    public  String email;
    public  String phoneNumber;

    public Ad(Car car){
        this.car = car;
    }

    public void AttachInformationData(Date pickupDate, Date returnDate, String firstName, String lastName, String email, String phoneNumber) {
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
