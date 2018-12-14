package com.example.essam.hospitalscover.View;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
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
import com.example.essam.hospitalscover.webServicse.CheckRequestResponse;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements AdapterCategoryInterface {

    private RecyclerView recyclerCategory;
    private CategoryAdapter categoryAdapter;
    private CategoryModelView modelView;
    private List<CategoryData> categoryList;
    private ProgressBar progressBar;

    private Toolbar toolbar;
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    public static Location currentLocation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_page);

        //  requestPermissions();

        modelView = ViewModelProviders.of(this).get(CategoryModelView.class);
        initView();
        initListener();
        //
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            currentLocation = getLocation();

        }

        showProgress();
        modelView.chechBooking(MyMACAdress.getMacAddr());

    }

    private void initListener() {
        modelView.getCheck().observe(this, new Observer<CheckRequestResponse>() {
            @Override
            public void onChanged(@Nullable CheckRequestResponse checkRequestResponse) {
                hideProgress();
                if (checkRequestResponse != null) {
                    if (checkRequestResponse.redirect) {
                        gotDetailsActivity(checkRequestResponse);

                    } else {
                        showProgress();
                        modelView.getAllCategory(MyMACAdress.getMacAddr());
                    }

                } else {
                    Toast.makeText(HomePage.this, "Please Try Again.", Toast.LENGTH_SHORT).show();

                }
            }
        });

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

    private void gotDetailsActivity(CheckRequestResponse checkRequestResponse) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("booking", checkRequestResponse);
        startActivity(intent);
        finish();
    }


    private void setUpAdapter() {
        categoryAdapter = new CategoryAdapter(this, this);
        categoryAdapter.setData(categoryList);
        recyclerCategory.setAdapter(categoryAdapter);

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        recyclerCategory = (RecyclerView) findViewById(R.id.recycler_category);
        recyclerCategory.setHasFixedSize(true);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(toolbar);
        setTitle("Hospitals Cover");


    }

    @Override
    public void onCardClick(View view, int position) {
        goToSubCategory(position);
    }


    private void goToSubCategory(int position) {
        Intent intent = new Intent(this, SubCategory.class);
        intent.putExtra("name", categoryList.get(position).getName());
        intent.putExtra("id", categoryList.get(position).getId());
        intent.putExtra("icon", categoryList.get(position).getIcon());
        intent.putExtra("lat", currentLocation.getLatitude());
        intent.putExtra("long", currentLocation.getLongitude());
        startActivity(intent);
    }


    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    private Location getLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (location != null) {
                return location;


            } else if (location1 != null) {
                return location1;

            } else if (location2 != null) {
                return location2;
            } else {

                Toast.makeText(this, "Unable to Trace your location", Toast.LENGTH_SHORT).show();
            }
        }


        return null;
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

}
