package com.msku.example;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.msku.example.rentcar.R;

import org.w3c.dom.Text;

import java.util.List;

public class AdListAdapter extends RecyclerView.Adapter<AdListAdapter.ViewHolder> {
    private List<String> mCardTexts;
    private List<String>mCardImages;
    private List<String>mCardPrices;
    private List<String>mCardYear;
    private List<String>mCardMileage;
    private List<String>mCardManifacturer;
    private Context mContext;

    public AdListAdapter(Context context, List<String> cardTexts, List<String> cardImages,List<String> pricesTexts,List<String> yearTexts,List<String> mileageTexts,List<String> manifacturerTexts) {
        mCardTexts = cardTexts;
        mCardImages = cardImages;
        mCardPrices = pricesTexts;
        mCardYear = yearTexts;
        mCardMileage = mileageTexts;
        mCardManifacturer = manifacturerTexts;
        mContext = context;
    }
    @NonNull
    @Override
    public AdListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_card, parent, false);
        return new AdListAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull AdListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mCardText.setText(mCardTexts.get(position));
        holder.mCardImage.setImageURI(Uri.parse(mCardImages.get(position)));
        holder.mCardPrice.setText(mCardPrices.get(position));
        holder.mCardYear.setText(mCardYear.get(position));
        holder.mCardMileage.setText(mCardMileage.get(position));
        holder.mCardManifacturer.setText(mCardManifacturer.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagement.selectedAdID = position;
                Intent intent = new Intent(mContext, InformationAdActivity.class);
                mContext.startActivity(intent);
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
        public TextView mCardPrice;
        public TextView mCardYear;
        public TextView mCardMileage;
        public TextView mCardManifacturer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardText = itemView.findViewById(R.id.vehicleName);
            mCardImage = itemView.findViewById(R.id.vehicleCategoryImageAD);
            mCardPrice = itemView.findViewById(R.id.quantity);
            mCardYear = itemView.findViewById(R.id.year);
            mCardMileage = itemView.findViewById(R.id.mileageText);
            mCardManifacturer = itemView.findViewById(R.id.manifacturerText);
        }



    }
}
