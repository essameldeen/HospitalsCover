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
import com.example.essam.hospitalscover.R;

import java.util.ArrayList;
import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<CategoryData> categoryList;
    Context context;
    AdapterCategoryInterface anInterface;

    public CategoryAdapter(Context context ,AdapterCategoryInterface  anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }

    public void setData(List<CategoryData> categories) {
        this.categoryList = new ArrayList<>();
        categoryList.addAll(categories);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_single_categry, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        CategoryData item = categoryList.get(position);

        if (item.getName() != null) {
            viewHolder.categoryName.setText(item.getName());
        }

        if (item.getIcon() != null) {
            Glide.with(context).load(item.getIcon()).into(viewHolder.categoryImage);
        }

    }

    @Override
    public int getItemCount() {
        if (categoryList != null) {
            return categoryList.size();
        } else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.name_category);
            categoryImage = itemView.findViewById(R.id.image_category);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    anInterface.onCardClick(itemView,getAdapterPosition());
                }
            });
        }
    }
}
