package com.msku.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.msku.example.rentcar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddVehicleActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 1889;

    Button loadImageButton;
    Button addButton;
    private ImageView viewVehicle;
    String categoryText;
    EditText priceEditText;
    EditText mileageEditText;
    EditText manufacturerEditText;
    EditText modelEditText;
    EditText yearEditText;
    Uri selectedUri;
//ana sayfa nerde.. ilk açılan loginden sonra
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        Spinner categorySpinner = findViewById(R.id.spinner);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                categoryText = text;            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayList<String> categories = new ArrayList<>();
        categories.add("SUV");
        categories.add("Super Sport");
        categories.add("Hatchback");
        categories.add("Coupe");
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,categories);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        categorySpinner.setAdapter(adapter);
        loadImageButton = findViewById(R.id.load);
        viewVehicle = findViewById(R.id.viewVehicle);
        priceEditText = findViewById(R.id.price);
        mileageEditText = findViewById(R.id.mileage);
        manufacturerEditText = findViewById(R.id.manufacturer);
        modelEditText = findViewById(R.id.model);
        yearEditText = findViewById(R.id.year);
        addButton = findViewById(R.id.add);
        LoadImageButtonClick();
        AddVehicleButtonClick();
    }

    private void LoadImageButtonClick() {
        loadImageButton.setOnClickListener(view ->{
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, GALLERY_REQUEST);
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            selectedUri = selectedImage;
            viewVehicle.setImageURI(selectedImage);
        }
    }

    private void AddVehicleButtonClick() {
        addButton.setOnClickListener(view ->{
            String category = "";
            String price = "";
            String mileage = "";
            String manufacturer = "";
            String model = "";
            String year = "";
            price = String.valueOf(priceEditText.getText());
            mileage = String.valueOf(mileageEditText.getText());
            manufacturer = String.valueOf(manufacturerEditText.getText());
            model = String.valueOf(modelEditText.getText());
            year = String.valueOf(yearEditText.getText());
            category = categoryText;
            Car car = new Car(category,price,mileage,manufacturer,model,year,selectedUri.toString());

            uploadCar_saveFirebase(car, selectedUri);

//            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.getUid());
//            userRef.child("cars").push().setValue(car)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            // Car added successfully
//                            Toast.makeText(AddVehicleActivity.this,"Car added",Toast.LENGTH_SHORT).show();
//                            Intent intent =new Intent(getApplicationContext(),OwnersCar.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            // Handle error
//                            Toast.makeText(AddVehicleActivity.this,"Error adding car",Toast.LENGTH_SHORT).show();
//                            Log.e("TAG", "Error adding car: ", e);
//                        }
//                    });
        });// gi
    }

    private void uploadCar_saveFirebase(Car car, Uri selectedUri) { // o yüzden önce firebase storege ya resim yüklenecek
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference carPath = storageReference.child("arabalar");
        StorageReference resim = carPath.child(car.price); //buna unik bir deger vermem lazım ne olsun?// bizde mesela sistem. milisaniye dedim



                resim.putFile(selectedUri).addOnCompleteListener(task ->{
           if(task.isSuccessful()){
               resim.getDownloadUrl().addOnSuccessListener(uri -> {
                  String url = uri.toString();
                  uploadToFirestore(url,car);
               });
               Log.e("resim","resim yüklendi"); //yükledi şimdi ama url almam gerek firesoreye kayıt için

            }
        });


    }

    private void uploadToFirestore(String url, Car car) {
        FirebaseAuth mAut = FirebaseAuth.getInstance();

        if(mAut.getCurrentUser()!=null){
            Map<String, Object> carMap = new HashMap<>();
            carMap.put("category",car.category ); // bu fieldları doldursan olur mu bilmiyorm ben isimleri şimdi mi evet
            carMap.put("manifacturer", car.manufacturer);
            carMap.put("mileage", car.mileage);
            carMap.put("price", car.price);
            carMap.put("year", car.year);
            carMap.put("imageURL", url);
            carMap.put("model", car.model);
            carMap.put("userId", mAut.getCurrentUser().getUid());

            FirebaseFirestore database = FirebaseFirestore.getInstance();

            database.collection("cars")
                    .add(carMap)
                    .addOnSuccessListener(documentReference -> {
                        documentReference.update("car_id",documentReference.getId());
                        Toast.makeText(this, "Araç başarıyla kaydedildi", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Firestore kayıt hatası", Toast.LENGTH_SHORT).show());
            startActivity(new Intent(this, LoginActivity.class));

        }else {
            Toast.makeText(this, "Tekrar giriş yapınız", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
        }
       }



    private void WaitForCarList () {
        try {
            Thread.sleep (2 * 1000);
            startActivity(new Intent(this,OwnersCar.class));
        } catch (InterruptedException ex) {
            System.err.println (ex);
        }
    }
}