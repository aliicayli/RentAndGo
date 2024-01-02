package com.msku.example;

import android.content.Context;
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

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mCardText.setText(mCardTexts.get(position));
        holder.mCardPrice.setText(mCardPrices.get(position));
        //holder.mCardImage.setImageResource(mCardImages.get(position));
        holder.mCardImage.setImageURI(mCardImages.get(position));
        holder.mCardYear.setText(mCardYears.get(position));
    }

    @Override
    public int getItemCount() {
        return mCardTexts.size();
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
            mCardImage = itemView.findViewById(R.id.categoryImage);
            mCardYear = itemView.findViewById(R.id.year);
        }
    }
}
