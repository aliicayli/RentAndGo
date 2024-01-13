package com.msku.example;

import android.content.Context;
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

public class CarCategoryAdapter extends RecyclerView.Adapter<CarCategoryAdapter.ViewHolder> {
    private List<String> mCardTexts;
    private List<Integer>mCardImages;
    private Context mContext;
    public CarCategoryAdapter(Context context, List<String> cardTexts, List<Integer> cardImages) {
        mCardTexts = cardTexts;
        mCardImages = cardImages;
        mContext = context;
    }
    @NonNull
    @Override
    public CarCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);
        return new CarCategoryAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull CarCategoryAdapter.ViewHolder holder, int position) {
        holder.mCardText.setText(mCardTexts.get(position));
        holder.mCardImage.setImageResource(mCardImages.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagement.selectedCategory = holder.mCardText.getText().toString();
                Intent intent = new Intent(mContext, AdListActivity.class);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardText = itemView.findViewById(R.id.categoryTextHeader);
            mCardImage = itemView.findViewById(R.id.vehicleCategoryImage);
        }



    }
}