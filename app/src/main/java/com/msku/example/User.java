package com.msku.example;

import java.util.ArrayList;

public class User {
    public String fullName;
    public String email;
    public String dateOfBirth;
    public String phoneNumber;
    public String city;
    public String password;
    public ArrayList<Car> cars = new ArrayList<>();

    public User(String fullName,String email,String dateOfBirth, String phoneNumber, String city, String password,ArrayList<Car> cars){
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.password = password;
        this.cars = cars;
    }
    public User() {
        // Gerekirse burada default değerler atayın
    }
}
