package com.example.essam.hospitalscover.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.essam.hospitalscover.Interfaces.AdapterCategoryInterface;
import com.example.essam.hospitalscover.Model.CategoryData;
import com.example.essam.hospitalscover.Model.SubCategory;
import com.example.essam.hospitalscover.Model.SubCategoryData;
import com.example.essam.hospitalscover.R;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {
    List<SubCategoryData> subCategoryList;
    Context context;
    AdapterCategoryInterface anInterface;

    public SubCategoryAdapter(Context context, AdapterCategoryInterface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }

    public void setData(List<SubCategoryData> categories) {
        this.subCategoryList = new ArrayList<>();
        subCategoryList.addAll(categories);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_singele_sub_category, viewGroup, false);
        return new SubCategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryAdapter.ViewHolder viewHolder, int position) {
        SubCategoryData item = subCategoryList.get(position);

        if (item.getName() != null) {
            viewHolder.subCategoryName.setText(item.getName());
        }


    }

    @Override
    public int getItemCount() {
        if (subCategoryList != null) {
            return subCategoryList.size();
        } else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subCategoryName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subCategoryName = itemView.findViewById(R.id.name_subCategory);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    anInterface.onCardClick(itemView, getAdapterPosition());
                }
            });
        }
    }
}
