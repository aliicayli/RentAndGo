package com.msku.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.msku.example.rentcar.R;

import java.util.ArrayList;
import java.util.List;

public class CarCategoryActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private CarCategoryAdapter ownersCarAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_category);
        recycler_view = findViewById(R.id.recycler_view);

        List<String> cardTexts = new ArrayList<>();
       cardTexts.add("Togg");

        List<Integer> cardImages = new ArrayList<>();

            cardImages.add(R.drawable.car_togg);



        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        ownersCarAdapter = new CarCategoryAdapter(cardTexts ,cardImages);
        recycler_view.setAdapter(ownersCarAdapter);

    }
}