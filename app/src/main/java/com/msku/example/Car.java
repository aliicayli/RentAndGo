package com.msku.example;

import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.EditText;

public class Car {
    int id;
    String category;
    String price;
    String mileage;
    String manufacturer;
    String model;
    String year;
    Bitmap carImage;

    public Car(int id,String category, String price, String mileage, String manufacturer, String model, String year,Bitmap carImage) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.mileage = mileage;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.carImage = carImage;
    }
}
