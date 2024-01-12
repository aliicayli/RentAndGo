package com.msku.example;

import android.content.Intent;
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

public class AdListAdapter extends RecyclerView.Adapter<AdListAdapter.ViewHolder> {
    private List<String> mCardTexts;
    private List<Uri>mCardImages;
    public AdListAdapter(List<String> cardTexts, List<Uri> cardImages) {
        mCardTexts = cardTexts;
        mCardImages = cardImages;
    }
    @NonNull
    @Override
    public AdListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_card, parent, false);
        return new AdListAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull AdListAdapter.ViewHolder holder, int position) {
        holder.mCardText.setText(mCardTexts.get(position));
        holder.mCardImage.setImageURI(mCardImages.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mCardTexts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mCardText;
        public ImageView mCardImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardText = itemView.findViewById(R.id.categoryTextHeaderAD);
            mCardImage = itemView.findViewById(R.id.vehicleCategoryImageAD);
        }



    }
}
