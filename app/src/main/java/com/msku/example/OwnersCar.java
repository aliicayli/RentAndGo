package com.msku.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.msku.example.rentcar.R;

import java.util.ArrayList;
import java.util.List;

public class OwnersCar extends AppCompatActivity {

    private RecyclerView recycler_view;
    private OwnersCarAdapter ownersCarAdapter;
    private OwnersCarAdapter2 ownersCarAdapter2;

    ArrayList<Car> carlist = new ArrayList<>(); // bu listenin bir görevi uok sadece içine bir liste yazmam gerektiği için

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_car);
        recycler_view = findViewById(R.id.recycler_view2);
        ownersCarAdapter2 = new OwnersCarAdapter2(this, carlist);


//        List<String> cardTexts = new ArrayList<>();
//        for (int i = 0; i < UserManagement.users.get(UserManagement.loggedEmail).cars.size(); i++) {
//            cardTexts.add(UserManagement.users.get(UserManagement.loggedEmail).cars.get(i).model);
//        }
//        List<String> cardPrices = new ArrayList<>();
//        for (int i = 0; i < UserManagement.users.get(UserManagement.loggedEmail).cars.size(); i++) {
//            cardPrices.add(UserManagement.users.get(UserManagement.loggedEmail).cars.get(i).price);
//        }
//
//
//        List<Uri> cardImages = new ArrayList<>();
//        for (int i = 0; i < UserManagement.users.get(UserManagement.loggedEmail).cars.size(); i++) {
//            cardImages.add(Uri.parse(UserManagement.users.get(UserManagement.loggedEmail).cars.get(i).carImage));
//        }
//
//
//        List<String> cardYears = new ArrayList<>();
//        for (int i = 0; i < UserManagement.users.get(UserManagement.loggedEmail).cars.size(); i++) {
//            cardYears.add(UserManagement.users.get(UserManagement.loggedEmail).cars.get(i).year);
//        }

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
       // ownersCarAdapter = new OwnersCarAdapter(cardTexts,cardPrices ,cardImages,cardYears);
        recycler_view.setAdapter(ownersCarAdapter2);

        getUserCars();


    }

    private void getUserCars() {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getUid();
        Log.e("kayıtlı kullanıcı id",userId);


        database.collection("cars").whereEqualTo("userId",userId).get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    //bu şarta uyan tüm dokümanları getirir
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Car car = new Car();
                        car.model = documentSnapshot.getString("model");
                        car.carImage = documentSnapshot.getString("imageURL");
                        car.category = documentSnapshot.getString("category");
                        car.price = documentSnapshot.getString("price");
                        car.mileage = documentSnapshot.getString("mileage");
                        car.manufacturer = documentSnapshot.getString("manifacturer");
                        car.year= documentSnapshot.getString("year");

                        ownersCarAdapter2.addCar(car);
                    }


                });
    }


}