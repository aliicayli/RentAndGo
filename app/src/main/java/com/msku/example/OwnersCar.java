package com.msku.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.msku.example.rentcar.R;

import java.util.ArrayList;
import java.util.List;

public class OwnersCar extends AppCompatActivity {

    private RecyclerView recycler_view;
    private OwnersCarAdapter ownersCarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_car);

        recycler_view = findViewById(R.id.recycler_view);

        List<String> cardTexts = new ArrayList<>();
        for (int i = 0; i < UserManagement.users.get(UserManagement.loggedEmail).cars.size(); i++) {
            cardTexts.add(UserManagement.users.get(UserManagement.loggedEmail).cars.get(i).model);
        }
        List<String> cardPrices = new ArrayList<>();
        for (int i = 0; i < UserManagement.users.get(UserManagement.loggedEmail).cars.size(); i++) {
            cardPrices.add(UserManagement.users.get(UserManagement.loggedEmail).cars.get(i).price);
        }


        List<Bitmap> cardImages = new ArrayList<>();
        for (int i = 0; i < UserManagement.users.get(UserManagement.loggedEmail).cars.size(); i++) {
            cardImages.add(UserManagement.users.get(UserManagement.loggedEmail).cars.get(i).carImage);
        }


        List<String> cardYears = new ArrayList<>();
        for (int i = 0; i < UserManagement.users.get(UserManagement.loggedEmail).cars.size(); i++) {
            cardYears.add(UserManagement.users.get(UserManagement.loggedEmail).cars.get(i).year);
        }

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        ownersCarAdapter = new OwnersCarAdapter(cardTexts,cardPrices ,cardImages,cardYears);
        recycler_view.setAdapter(ownersCarAdapter);

    }
}