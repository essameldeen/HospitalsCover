package com.example.essam.hospitalscover.View;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.example.essam.hospitalscover.ModelView.GoogleMapsNavigation;
import com.example.essam.hospitalscover.ModelView.MyMACAdress;
import com.example.essam.hospitalscover.R;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements AdapterCategoryInterface {

    private RecyclerView recyclerCategory;
    private CategoryAdapter categoryAdapter;
    private CategoryModelView modelView;
    private List<CategoryData> categoryList;
    private ProgressBar progressBar;
/*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==0)
        {
            // lat,long
            //public static String drivingURL="https://www.google.com/maps/dir/?api=1&origin=30.0469686,31.3463023&destination=30.0602538,31.2025336&travelmode=driving";

            //get redirect to route google maps
            GoogleMapsNavigation googleMapsNavigation= new GoogleMapsNavigation(this,30.0,31.0);
            startActivity( googleMapsNavigation.getGoogleMapsIntentAfterInit());

        }
    }
*/
    public void requestPermissions()
    {
        boolean gpsRequest=true;
        int permissionCheckGPS = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheckGPS != PackageManager.PERMISSION_GRANTED ) {
            gpsRequest=true;
        }

        if (gpsRequest)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
        }
        else
        {
            // lat,long
            //public static String drivingURL="https://www.google.com/maps/dir/?api=1&origin=30.0469686,31.3463023&destination=30.0602538,31.2025336&travelmode=driving";

            //get redirect to route google maps
            GoogleMapsNavigation googleMapsNavigation= new GoogleMapsNavigation(this,30.0,31.0);
            startActivity( googleMapsNavigation.getGoogleMapsIntentAfterInit());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_page);

      //  requestPermissions();

        modelView = ViewModelProviders.of(this).get(CategoryModelView.class);
        initView();
        initListener();

        showProgress();
        modelView.getAllCategory();

        GoogleMapsNavigation googleMapsNavigation= new GoogleMapsNavigation(this,30.0,31.0);
        startActivity( googleMapsNavigation.getGoogleMapsIntentAfterInit());


        //get mac address
        String macAddress= MyMACAdress.getMacAddr();
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
