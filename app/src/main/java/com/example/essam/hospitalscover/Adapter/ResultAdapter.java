package com.example.essam.hospitalscover.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.essam.hospitalscover.Interfaces.AdapterCategoryInterface;
import com.example.essam.hospitalscover.Model.Result;
import com.example.essam.hospitalscover.Model.ResultData;
import com.example.essam.hospitalscover.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    Context context;
    List<ResultData> resultDatalist;
    AdapterCategoryInterface anInterface;

    public ResultAdapter(Context context, AdapterCategoryInterface anInterface) {
        this.anInterface = anInterface;
        this.context = context;
    }

    public void setData(List<ResultData> resultData) {
        this.resultDatalist = new ArrayList<>();
        resultDatalist.clear();
        resultDatalist.addAll(resultData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_single_result, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ResultData item = resultDatalist.get(position);

        if (item.getName() != null)
            viewHolder.hospital_name.setText(item.getName());
        if (item.getAddress() != null)
            viewHolder.hospital_address.setText(item.getAddress());
        if (item.getImage() != null) {
            Glide.with(context).load(item.getImage()).into(viewHolder.hospital_image);
        }
        int x = item.getRating().intValue();
        viewHolder.hospital_rating.setText("rating:" + x +" /5" + "  ");


    }

    @Override
    public int getItemCount() {
        if (this.resultDatalist != null) {
            return resultDatalist.size();
        } else
            return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView hospital_image;
        private TextView hospital_name;
        private TextView hospital_address;
        private AppCompatButton booking;
        private AppCompatImageButton call;
        private AppCompatImageButton nav;
        private TextView hospital_rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hospital_image = (CircleImageView) itemView.findViewById(R.id.image_hospitals);
            hospital_name = (TextView) itemView.findViewById(R.id.name_hospital);
            hospital_address = (TextView) itemView.findViewById(R.id.hospital_address);
            booking = (AppCompatButton) itemView.findViewById(R.id.booking);
            call = (AppCompatImageButton) itemView.findViewById(R.id.call);
            nav = (AppCompatImageButton) itemView.findViewById(R.id.nav_map);
            hospital_rating = (TextView) itemView.findViewById(R.id.hospital_rating);

            nav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    anInterface.onCardClick(nav, getAdapterPosition());
                }
            });

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    anInterface.onCardClick(call, getAdapterPosition());
                }
            });
            booking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    anInterface.onCardClick(booking, getAdapterPosition());
                }
            });


        }
    }
}
