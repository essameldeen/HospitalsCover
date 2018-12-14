package com.example.essam.hospitalscover.View;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.essam.hospitalscover.Adapter.SubCategoryAdapter;
import com.example.essam.hospitalscover.Interfaces.AdapterCategoryInterface;
import com.example.essam.hospitalscover.Model.Result;
import com.example.essam.hospitalscover.Model.ResultData;
import com.example.essam.hospitalscover.Model.SubCategoryData;
import com.example.essam.hospitalscover.ModelView.MyMACAdress;
import com.example.essam.hospitalscover.ModelView.SubCategoryModelView;
import com.example.essam.hospitalscover.R;
import com.example.essam.hospitalscover.webServicse.FilterRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SubCategory extends AppCompatActivity implements AdapterCategoryInterface {
    private RecyclerView recyclerSubCategory;
    private SubCategoryAdapter subCategoryAdapter;
    private ImageView imageSubCategory;
    private TextView nameSubCategory;
    private SubCategoryModelView modelView;
    private List<SubCategoryData> subCategoryDataList;
    private ProgressBar progressBar;
    private String idCategory;
    private String idSubCategory;
    private Toolbar toolbar;
    private double LONG, LAT;
    String name;
    String icon;
    AlertDialog dialog;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sub_category);
        modelView = ViewModelProviders.of(this).get(SubCategoryModelView.class);
        initView();
        initListener();


         name = getIntent().getStringExtra("name");
         icon = getIntent().getStringExtra("icon");
        idCategory = getIntent().getStringExtra("id");
        LONG = getIntent().getDoubleExtra("long", -1);
        LAT = getIntent().getDoubleExtra("lat", -1);

        if (icon != null)
            Glide.with(this).load(icon).into(imageSubCategory);
        if (name != null)
            nameSubCategory.setText(name);
        else
            nameSubCategory.setText("Sub Category");


        showProgress();
        modelView.getAllSubCategory(idCategory);

    }

    private void initListener() {

        modelView.getResult().observe(this, new Observer<Result>() {
            @Override
            public void onChanged(@Nullable Result result) {
                hideDialog();
                if (result != null) {
                    if (result.getData() != null && result.getData().size() > 0) {
                        gotToResultActivity(result.getData());

                    } else {
                        Toast.makeText(SubCategory.this, "Sorry No Hospitals Find.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SubCategory.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                }


            }
        });
        modelView.getSubCategory().observe(this, new Observer<com.example.essam.hospitalscover.Model.SubCategory>() {
            @Override
            public void onChanged(@Nullable com.example.essam.hospitalscover.Model.SubCategory subCategory) {
                hideProgress();
                if (subCategory != null) {
                    subCategoryDataList = new ArrayList<>();
                    subCategoryDataList.clear();
                    subCategoryDataList.addAll(subCategory.getData());
                    setUpAdapter();

                } else {
                    Toast.makeText(SubCategory.this, "Please Try Again.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void gotToResultActivity(List<ResultData> data) {
        ArrayList<ResultData> list = (ArrayList<ResultData>) data;
        Intent intent = new Intent(this, ResultActiviyt.class);
        intent.putExtra("data", (Serializable) list);
        intent.putExtra("id", idSubCategory);
        intent.putExtra("subCategoryName",name);
        intent.putExtra("subCategoryImage",icon);
        startActivity(intent);
    }

    private void setUpAdapter() {
        subCategoryAdapter.setData(subCategoryDataList);
    }

    @SuppressLint("RestrictedApi")
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        imageSubCategory = (ImageView) findViewById(R.id.image_subCategory);
        nameSubCategory = (TextView) findViewById(R.id.name_subCategory);
        recyclerSubCategory = (RecyclerView) findViewById(R.id.recycler_subCategory);
        recyclerSubCategory.setHasFixedSize(true);
        recyclerSubCategory.setLayoutManager(new LinearLayoutManager(this));
        subCategoryAdapter = new SubCategoryAdapter(this, this);
        recyclerSubCategory.setAdapter(subCategoryAdapter);

        setSupportActionBar(toolbar);
        setTitle("Hospitals Cover");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onCardClick(View view, int position) {

        showDialog();
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.destination.latitude = LAT;
        filterRequest.destination.longitude = LONG;
        filterRequest.subCategoryId = subCategoryDataList.get(position).getId();
        idSubCategory = subCategoryDataList.get(position).getId();
        modelView.getFilterHospitals(MyMACAdress.getMacAddr(),filterRequest);

    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    private void showDialog() {

        builder = new AlertDialog.Builder(this, R.style.AlertDialog)
                .setTitle("Pleas Waiting ")
                .setMessage("Searching for your Needs...")

                .setCancelable(false);

        dialog = builder.show();
    }

    private void hideDialog() {
        dialog.dismiss();
    }
}
