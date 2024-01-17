package com.msku.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.msku.example.rentcar.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AdListActivity extends AppCompatActivity {
    private RecyclerView recycler_view;
    //private AdListAdapter ownersCarAdapter;
    ArrayList<Ad> adList = new ArrayList<>();
    private AdListAdapter2 ownersCarAdapter;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_list);
        intent = getIntent();
        recycler_view = findViewById(R.id.recycler_viewAd);



//        List<String> cardTexts = new ArrayList<>();
//        List<String> cardPrices = new ArrayList<>();
//        List<String> cardYears = new ArrayList<>();
//        List<String> cardMileages = new ArrayList<>();
//        List<String> cardManifacturer = new ArrayList<>();
//        List<Uri> cardImages = new ArrayList<>();
//        for (HashMap.Entry<Integer, Ad> entry : UserManagement.ads.entrySet()) {
//            Integer key = entry.getKey();
//            Ad value = entry.getValue();
//            if (UserManagement.ads.get(key).car.category.toString().equals(UserManagement.selectedCategory.toString())) {
//                cardTexts.add(UserManagement.ads.get(key).car.model);
//                cardPrices.add(UserManagement.ads.get(key).car.price);
//                cardYears.add(UserManagement.ads.get(key).car.year);
//                cardImages.add(Uri.parse(UserManagement.ads.get(key ).car.carImage));
//                cardMileages.add(UserManagement.ads.get(key ).car.mileage);
//                cardManifacturer.add(UserManagement.ads.get(key ).car.manufacturer);
//            }
//        }

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        ownersCarAdapter = new AdListAdapter2(this,adList);
        recycler_view.setAdapter(ownersCarAdapter);

        fetchCategoryCars(intent.getStringExtra("secilenKategori"));//bunu yazmıştık bir yerde
    }

    private void fetchCategoryCars(String category) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection("cars").whereEqualTo("category",category).get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    //bu şarta uyan tüm dokümanları getirir
                    for (QueryDocumentSnapshot documentCarSnapshot : queryDocumentSnapshots) {
                        Car car = new Car();
                        car.model = documentCarSnapshot.getString("model");
                        car.carImage = documentCarSnapshot.getString("imageURL");
                        car.category = documentCarSnapshot.getString("category");
                        car.price = documentCarSnapshot.getString("price");
                        car.mileage = documentCarSnapshot.getString("mileage");
                        car.manufacturer = documentCarSnapshot.getString("manifacturer");
                        car.year= documentCarSnapshot.getString("year");

                        String userId = documentCarSnapshot.getString("userId");

                        Log.e("gelen ilanların kullanıcıları :: ",userId);
                        Ad ad = new Ad(car);

                        database.collection("user").document(userId).get().addOnSuccessListener(
                                documentUserSnapshot ->{
                                    Date date = new Date();
                                    ad.AttachInformationData(
                                            date,
                                            date,
                                            documentUserSnapshot.getString("firstName"),
                                            documentUserSnapshot.getString("lastName"),
                                            documentUserSnapshot.getString("mail"),
                                            documentUserSnapshot.getString("phoneNumber")
                                    ); //pickup'u sonra set etcez. Çok önemli bir şey değil o
                                    ownersCarAdapter.addItem(ad);
                                }
                        );
                        //iyi tamam araç bilgileri vea ait olduğu kullanıcı ilgisi geririlid



                    }


                });

    }
}