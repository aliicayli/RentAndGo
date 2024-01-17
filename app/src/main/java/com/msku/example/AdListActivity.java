package com.msku.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.msku.example.rentcar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdListActivity extends AppCompatActivity {
    private RecyclerView recycler_view;
    private AdListAdapter ownersCarAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_list);
        recycler_view = findViewById(R.id.recycler_viewAd);

        List<String> cardTexts = new ArrayList<>();
        List<String> cardPrices = new ArrayList<>();
        List<String> cardYears = new ArrayList<>();
        List<String> cardMileages = new ArrayList<>();
        List<String> cardManifacturer = new ArrayList<>();
        List<String> cardImages = new ArrayList<>();
        for (HashMap.Entry<Integer, Ad> entry : UserManagement.ads.entrySet()) {
            Integer key = entry.getKey();
            Ad value = entry.getValue();
            if (UserManagement.ads.get(key).car.category.toString().equals(UserManagement.selectedCategory.toString())) {
                cardTexts.add(UserManagement.ads.get(key).car.model);
                cardPrices.add(UserManagement.ads.get(key).car.price);
                cardYears.add(UserManagement.ads.get(key).car.year);
                cardImages.add(UserManagement.ads.get(key ).car.carImage.toString());
                cardMileages.add(UserManagement.ads.get(key ).car.mileage);
                cardManifacturer.add(UserManagement.ads.get(key ).car.manufacturer);
            }
        }

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        ownersCarAdapter = new AdListAdapter(this,cardTexts,cardImages,cardPrices,cardYears,cardMileages,cardManifacturer);
        recycler_view.setAdapter(ownersCarAdapter);
    }
}