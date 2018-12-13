package com.example.essam.hospitalscover.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.essam.hospitalscover.Adapter.SubCategoryAdapter;
import com.example.essam.hospitalscover.R;

public class SubCategory extends AppCompatActivity {
    private RecyclerView recyclerSubCategory;
    private SubCategoryAdapter subCategoryAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sub_category);
    }
}
