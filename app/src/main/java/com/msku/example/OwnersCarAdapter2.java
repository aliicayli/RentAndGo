package com.msku.example;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.msku.example.rentcar.R;

import java.util.ArrayList;

public class OwnersCarAdapter2 extends RecyclerView.Adapter<OwnersCarAdapter2.ViewHolder> {
    private ArrayList<Car> carList;
    Context mContext;
    public OwnersCarAdapter2(Context context, ArrayList<Car> carList) {
        this.carList = carList;
        this.mContext =context;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.mCardText.setText(car.model);
        holder.mCardPrice.setText(car.price);
        //holder.mCardImage.setImageResource(mCardImages.get(position)); // burası şey sade kütüphane içine paramaete olarak açtivity istediği için .. context de activity işte o classı temsil eden şey
        Glide.with(mContext).load(car.carImage).into(holder.mCardImage); //bu kadar.. anlaşılır zaten anladın demieeweet
        // holder.mCardImage.setImageURI(Uri.parse(car.carImage));
        holder.mCardYear.setText(car.year);
    } // anladım..

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public void addCar(Car car) {
        Log.e("CALIŞTIIIII","CALIŞTIIIII");
        carList.add(car);
        notifyItemChanged(carList.size()-1);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mCardText;
        public TextView mCardPrice;
        public ImageView mCardImage;
        public TextView mCardYear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardText = itemView.findViewById(R.id.vehicleName);
            mCardPrice= itemView.findViewById(R.id.quantity);
            mCardImage = itemView.findViewById(R.id.vehicleCategoryImage);
            mCardYear = itemView.findViewById(R.id.year);
        }
    }
}
