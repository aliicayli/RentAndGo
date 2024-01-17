package com.msku.example;

import android.content.Context;
import android.content.Intent;
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

public class AdListAdapter2 extends RecyclerView.Adapter<AdListAdapter2.ViewHolder2> {

    private ArrayList<Ad> adList;
    Context mContext;
    public AdListAdapter2(Context context, ArrayList<Ad> adList) {
        this.adList = adList;
        this.mContext =context;
    }

    @NonNull
    @Override
    public AdListAdapter2.ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_card, parent, false);
        return new AdListAdapter2.ViewHolder2(view);    }

    @Override
    public void onBindViewHolder(@NonNull AdListAdapter2.ViewHolder2 holder, int position) {
        Ad currentAd = adList.get(position);
        holder.mCardText.setText(currentAd.car.model);
        Glide.with(mContext).load(currentAd.car.carImage).into(holder.mCardImage);
        holder.mCardPrice.setText(currentAd.car.price);
        holder.mCardYear.setText(currentAd.car.mileage);
        holder.mCardMileage.setText(currentAd.car.mileage);
        holder.mCardManifacturer.setText(currentAd.car.manufacturer);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagement.selectedAdID = position;
                Intent intent = new Intent(mContext, InformationAdActivity.class);
                System.out.println("--- >>>>  "+currentAd.firstName);
                intent.putExtra("firstName",currentAd.firstName);
                intent.putExtra("lastName",currentAd.lastName);
                intent.putExtra("phoneNumber",currentAd.phoneNumber);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return adList.size();
    }

    public void addItem(Ad ad) {
        adList.add(ad);
        notifyItemInserted(adList.size()-1);
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder {

        public TextView mCardText;
        public ImageView mCardImage;
        public TextView mCardPrice;
        public TextView mCardYear;
        public TextView mCardMileage;
        public TextView mCardManifacturer;

        public ViewHolder2(@NonNull View itemView) {
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
