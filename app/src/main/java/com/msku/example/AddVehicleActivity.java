package com.msku.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.msku.example.rentcar.R;

import java.util.ArrayList;

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
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            String userID = currentUser.getUid();

            Car car = new Car(category,price,mileage,manufacturer,model,year,selectedUri);



            FirebaseDatabase database = FirebaseDatabase.getInstance();
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            String userId = firebaseUser.getUid();
            DatabaseReference reference = database.getReference("Users").child(userId).child("togg");
            reference.setValue(car.carImage.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(AddVehicleActivity.this,"teadasd",Toast.LENGTH_SHORT).show();
                }
            });
            //Toast.makeText(this,test,Toast.LENGTH_SHORT);




        });
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