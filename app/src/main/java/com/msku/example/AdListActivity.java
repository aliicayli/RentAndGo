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
        recycler_view = findViewById(R.id.recycler_view);

        List<String> cardTexts = new ArrayList<>();
        List<Uri> cardImages = new ArrayList<>();
        for (HashMap.Entry<Integer, Ad> entry : UserManagement.ads.entrySet()) {
            Integer key = entry.getKey();
            Ad value = entry.getValue();
            if (UserManagement.ads.get(key).car.category.toString().equals(UserManagement.selectedCategory.toString())) {
                cardTexts.add(UserManagement.ads.get(key).car.model);
                cardImages.add(UserManagement.ads.get(key).car.carImage);
            }
        }

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        ownersCarAdapter = new AdListAdapter(cardTexts,cardImages);
        recycler_view.setAdapter(ownersCarAdapter);
    }
}