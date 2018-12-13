package com.example.essam.hospitalscover.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.essam.hospitalscover.ModelView.SubCategoryModelView;
import com.example.essam.hospitalscover.R;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sub_category);
        modelView = ViewModelProviders.of(this).get(SubCategoryModelView.class);
        initView();
        initListener();


        String name = getIntent().getStringExtra("name");
        String icon = getIntent().getStringExtra("icon");
        idCategory = getIntent().getStringExtra("id");

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
                if (result != null) {
                    if (result.getData() != null && result.getData().size() > 0) {
                        //Show Dialog
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
        Intent intent = new Intent(this, ResultActiviyt.class);
        // intent.putStringArrayListExtra("results",data);
    }

    private void setUpAdapter() {
        subCategoryAdapter.setData(subCategoryDataList);
    }

    private void initView() {
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        imageSubCategory = (ImageView) findViewById(R.id.image_subCategory);
        nameSubCategory = (TextView) findViewById(R.id.name_subCategory);
        recyclerSubCategory = (RecyclerView) findViewById(R.id.recycler_subCategory);
        recyclerSubCategory.setHasFixedSize(true);
        recyclerSubCategory.setLayoutManager(new LinearLayoutManager(this));

        subCategoryAdapter = new SubCategoryAdapter(this, this);

        recyclerSubCategory.setAdapter(subCategoryAdapter);

    }

    @Override
    public void onCardClick(View view, int position) {


    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
