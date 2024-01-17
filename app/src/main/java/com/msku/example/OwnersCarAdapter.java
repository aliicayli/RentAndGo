package com.msku.example;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.msku.example.rentcar.R;

import java.util.List;

public class OwnersCarAdapter extends RecyclerView.Adapter<OwnersCarAdapter.ViewHolder> {
    private List<Car> carList;

    private List<String> mCardTexts;
    private List<String> mCardPrices;
    private List<Uri> mCardImages;
    private List<String> mCardYears;
    public OwnersCarAdapter(List<String> cardTexts,List<String> cardPrices, List<Uri> cardImages,List<String> cardYears) {
        mCardTexts = cardTexts;
        mCardPrices= cardPrices;
        mCardImages = cardImages;
        mCardYears = cardYears;
    }

    public OwnersCarAdapter(List<Car> carList) {
        this.carList = carList;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Car  car = carList.get(position);
        holder.mCardText.setText(car.model);
        holder.mCardPrice.setText(car.price);
        //holder.mCardImage.setImageResource(mCardImages.get(position));
        holder.mCardImage.setImageURI(Uri.parse(car.carImage));
        holder.mCardYear.setText(car.year);
    }

    @Override
    public int getItemCount() {
        return carList.size();
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
