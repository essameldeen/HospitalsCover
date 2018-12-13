package com.example.essam.hospitalscover.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.essam.hospitalscover.Adapter.CategoryAdapter;
import com.example.essam.hospitalscover.Interfaces.AdapterCategoryInterface;
import com.example.essam.hospitalscover.Model.Category;
import com.example.essam.hospitalscover.Model.CategoryData;
import com.example.essam.hospitalscover.ModelView.CategoryModelView;
import com.example.essam.hospitalscover.R;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements AdapterCategoryInterface {

    private RecyclerView recyclerCategory;
    private CategoryAdapter categoryAdapter;
    private CategoryModelView modelView;
    private List<CategoryData> categoryList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_page);
        modelView = ViewModelProviders.of(this).get(CategoryModelView.class);
        initView();
        initListener();

        // request for all category
        showProgress();
        modelView.getAllCategory();

    }

    private void initListener() {

        modelView.getCategory().observe(this, new Observer<Category>() {
                    @Override
                    public void onChanged(@Nullable Category categories) {
                        hideProgress();
                        if (categories != null) {
                            categoryList = new ArrayList<>();
                            categoryList.clear();
                            categoryList = categories.getData();
                            setUpAdapter();

                        } else {
                            Toast.makeText(HomePage.this, "Please Try Again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }


    private void setUpAdapter() {
        categoryAdapter = new CategoryAdapter(this, this);
        categoryAdapter.setData(categoryList);
        recyclerCategory.setAdapter(categoryAdapter);

    }

    private void initView() {
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        recyclerCategory = (RecyclerView) findViewById(R.id.recycler_category);
        recyclerCategory.setHasFixedSize(true);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onCardClick(View view, int position) {
        Toast.makeText(this, categoryList.get(position).getName() + " " + categoryList.get(position).getId(), Toast.LENGTH_SHORT).show();

    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }
    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
