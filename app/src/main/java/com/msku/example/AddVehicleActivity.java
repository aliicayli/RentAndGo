package com.msku.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.msku.example.rentcar.R;

public class AddVehicleActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 1889;

    Button loadImageButton;
    Button addButton;
    private ImageView viewVehicle;
    EditText categoryEditText;
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
        loadImageButton = findViewById(R.id.load);
        viewVehicle = findViewById(R.id.viewVehicle);
        categoryEditText = findViewById(R.id.category);
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
            if (categoryEditText.getText() == null) category = "";
            else category = categoryEditText.getText().toString();

            if (priceEditText.getText() == null) price = "";
            else price = priceEditText.getText().toString();

            if (mileageEditText.getText() == null) mileage = "";
            else mileage = mileageEditText.getText().toString();

            if (manufacturerEditText.getText() == null) manufacturer = "";
            else manufacturer = manufacturerEditText.getText().toString();

            if (modelEditText.getText() == null) model = "";
            else model = modelEditText.getText().toString();

            if (yearEditText.getText() == null) year = "";
            else year = yearEditText.getText().toString();

            Car car = new Car(category,price,mileage,manufacturer,model,year,selectedUri);
            UserManagement.users.get(UserManagement.loggedEmail).cars.add(car) ;
            startActivity(new Intent(this,OwnersCar.class));
        });
    }
}