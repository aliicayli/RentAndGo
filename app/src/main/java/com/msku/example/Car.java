package com.msku.example;

import android.net.Uri;
import android.widget.EditText;

public class Car {
    String category;
    String price;
    String mileage;
    String manufacturer;
    String model;
    String year;
    Uri carImage;

    public Car(String category, String price, String mileage, String manufacturer, String model, String year,Uri carImage) {
        this.category = category;
        this.price = price;
        this.mileage = mileage;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.carImage = carImage;
    }
}