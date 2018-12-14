package com.example.essam.hospitalscover.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.essam.hospitalscover.ModelView.DetailsViewModel;
import com.example.essam.hospitalscover.ModelView.GoogleMapsNavigation;
import com.example.essam.hospitalscover.R;
import com.example.essam.hospitalscover.webServicse.BookingResponse;
import com.example.essam.hospitalscover.webServicse.CanceleRequest;
import com.example.essam.hospitalscover.webServicse.CheckRequestResponse;
import com.example.essam.hospitalscover.webServicse.Destination;
import com.example.essam.hospitalscover.webServicse.Reservation;

import java.io.Serializable;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.essam.hospitalscover.View.HomePage.currentLocation;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView subCategoryName;
    private ImageView subCategoryImage;
    private CircleImageView icon_hospital_image;
    private TextView hospital_name;
    private TextView hospital_rating;
    private AppCompatImageButton call;
    private AppCompatImageButton nav_map;
    private AppCompatButton cancele;
    private TextView hospital_address;
    private TextView hospital_phone;
    private TextView hospital_avilable;
    private BookingResponse bookingResponse;
    private DetailsViewModel detailsViewModel;
    private ProgressBar progressBar;
    private CheckRequestResponse checkRequestResponse;
    boolean areadyBooking = false;
    private String phone;
    private Double LONG, LAT;
    private Reservation cancelReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_details);
        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);

        initView();

        initListener();
        checkRequestResponse = (CheckRequestResponse) getIntent().getSerializableExtra("booking");
        if (checkRequestResponse != null) {
            areadyBooking = true;
            updataData(areadyBooking);

        } else {
            bookingResponse = (BookingResponse) getIntent().getSerializableExtra("result");
            areadyBooking = false;
            updataData();
        }

    }

    private void updataData(boolean areadyBooking) {
        subCategoryName.setText(checkRequestResponse.data.subCategoryName);
        Glide.with(this).load(checkRequestResponse.data.categoryIcon).into(subCategoryImage);

        hospital_name.setText(checkRequestResponse.data.hospitalName);
        Glide.with(this).load(checkRequestResponse.data.hospitalIcon).into(icon_hospital_image);
        hospital_address.setText("   " + checkRequestResponse.data.hospitalAddress);
        hospital_avilable.setText("   " + checkRequestResponse.data.hospitalWorkingHours);
        hospital_rating.setText("rating:   " + checkRequestResponse.data.hospitalRating + "/5");
        hospital_phone.setText("   " + checkRequestResponse.data.hospitalPhone);
        phone = checkRequestResponse.data.hospitalPhone;
        LONG = checkRequestResponse.data.hospitalLongitude;
        LAT = checkRequestResponse.data.hospitalLatitude;

        cancelReservation = checkRequestResponse.data.reservation;
    }

    private void initListener() {
        detailsViewModel.getCancele().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                hideProgress();
                if (aBoolean) {
                    Toast.makeText(DetailsActivity.this, "Success Cancellation", Toast.LENGTH_SHORT).show();

                    goToHomePage();

                } else {
                    Toast.makeText(DetailsActivity.this, "Failed, Please Try Again.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void goToHomePage() {
        finish();
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    private void updataData() {
        String name = getIntent().getStringExtra("subCategoryName");
        String icon = getIntent().getStringExtra("subCategoryImage");
        subCategoryName.setText(name);
        Glide.with(this).load(icon).into(subCategoryImage);
        hospital_name.setText(bookingResponse.data.hospital.name);
        Glide.with(this).load(bookingResponse.data.hospital.image).into(icon_hospital_image);
        hospital_address.setText("   " + bookingResponse.data.hospital.address);
        hospital_avilable.setText("   OPEN");
        hospital_rating.setText("rating:   " + bookingResponse.data.hospital.rating + "/5");
        hospital_phone.setText("   " + bookingResponse.data.hospital.phone);
        phone = bookingResponse.data.hospital.phone;
        LONG = bookingResponse.data.hospital.destination.longitude;
        LAT = bookingResponse.data.hospital.destination.latitude;
        cancelReservation = bookingResponse.data.reservation;

    }

    private void initView() {
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        subCategoryName = (TextView) findViewById(R.id.name_subCategory);
        subCategoryImage = (ImageView) findViewById(R.id.image_subCategory);
        icon_hospital_image = (CircleImageView) findViewById(R.id.image_hospitals);
        hospital_name = (TextView) findViewById(R.id.name_hospital);
        hospital_rating = (TextView) findViewById(R.id.hospital_rating);
        hospital_address = (TextView) findViewById(R.id.hospital_address);
        hospital_phone = (TextView) findViewById(R.id.hospital_phone);
        hospital_avilable = (TextView) findViewById(R.id.hospital_avilable);

        call = (AppCompatImageButton) findViewById(R.id.call);
        nav_map = (AppCompatImageButton) findViewById(R.id.nav_map);
        cancele = (AppCompatButton) findViewById(R.id.cancele);
        call.setOnClickListener(this);
        nav_map.setOnClickListener(this);
        cancele.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call:
                callHospital();
                break;
            case R.id.nav_map:
                mapLocation();
                break;
            case R.id.cancele:
                canceleBooking();
                break;

        }
    }

    private void callHospital() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CALL_PHONE},
                    10);
            callHospital();
        } else {
            try {
                startActivity(callIntent);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getApplicationContext(), "Call failed", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void mapLocation() {
        GoogleMapsNavigation googleMapsNavigation = new GoogleMapsNavigation(this, currentLocation.getLatitude(), currentLocation.getLongitude(), LAT, LONG);
        startActivity(googleMapsNavigation.getGoogleMapsIntentAfterInit());
    }

    private void canceleBooking() {
        showProgress();
        CanceleRequest canceleRequest = new CanceleRequest();
        canceleRequest.reservation = cancelReservation;
        detailsViewModel.canceleBooking(canceleRequest);

    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
