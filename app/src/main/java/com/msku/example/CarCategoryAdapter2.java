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

import com.msku.example.rentcar.R;

import java.util.List;

public class CarCategoryAdapter2 extends RecyclerView.Adapter<CarCategoryAdapter2.ViewHolder> {

    public Context mContext;
    private List<String> cardTexts;
    private List<Integer> cardImages;

    public CarCategoryAdapter2(Context context, List<String> cardTexts, List<Integer> cardImages) {
        this.mContext = context;
        this.cardTexts = cardTexts;
        this.cardImages = cardImages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text = cardTexts.get(position);
        int imageResource = cardImages.get(position);

        holder.categoryTextTitle.setText(text);
        holder.vehicleCategoryImage.setImageResource(imageResource);
        holder.handleOnTap(mContext,text);
    }

    @Override
    public int getItemCount() {
        return cardTexts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView vehicleCategoryImage;
        TextView categoryTextTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vehicleCategoryImage = itemView.findViewById(R.id.vehicleCategoryImage2);
            categoryTextTitle = itemView.findViewById(R.id.categoryTextTitle);


        }

        public void handleOnTap(Context mContext, String title) {
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, AdListActivity.class);
                intent.putExtra("secilenKategori", title);
                mContext.startActivity(intent);
            });
        }
    }
}
