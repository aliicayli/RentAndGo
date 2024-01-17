package com.msku.example;

import android.net.Uri;
import android.widget.EditText;

public class Car {
    public String category;
    public String price;
    public String mileage;
    public String manufacturer;
    public String model;
    public String year;
    public String carImage;
    public Car() {}
    public Car(String category, String price, String mileage, String manufacturer, String model, String year,String carImage) {
        this.category = category;
        this.price = price;
        this.mileage = mileage;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.carImage = carImage;
    }
}
