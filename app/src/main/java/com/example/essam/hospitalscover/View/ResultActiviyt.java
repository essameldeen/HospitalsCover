package com.example.essam.hospitalscover.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.essam.hospitalscover.Adapter.ResultAdapter;
import com.example.essam.hospitalscover.Interfaces.AdapterCategoryInterface;
import com.example.essam.hospitalscover.Model.ResultData;
import com.example.essam.hospitalscover.ModelView.GoogleMapsNavigation;
import com.example.essam.hospitalscover.ModelView.MyMACAdress;
import com.example.essam.hospitalscover.ModelView.ResultViewModel;
import com.example.essam.hospitalscover.R;
import com.example.essam.hospitalscover.Repository.ResultRepo;
import com.example.essam.hospitalscover.webServicse.BookingResponse;
import com.example.essam.hospitalscover.webServicse.Destination;
import com.example.essam.hospitalscover.webServicse.RequestBooking;

import java.util.ArrayList;
import java.util.List;

import static com.example.essam.hospitalscover.View.HomePage.currentLocation;

public class ResultActiviyt extends AppCompatActivity implements AdapterCategoryInterface {

    private RecyclerView recycler_result;
    private ResultAdapter resultAdapter;
    private List<ResultData> resultDataList;
    private String macAddress;
    private String phone;
    private ResultViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_result_activity);
        viewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

        resultDataList = (List<ResultData>) getIntent().getSerializableExtra("data");
        macAddress = getIntent().getStringExtra("mac");

        initView();
        initListener();


    }

    private void initListener() {
        viewModel.getResult().observe(this, new Observer<BookingResponse>() {
            @Override
            public void onChanged(@Nullable BookingResponse bookingResponse) {
                //
            }
        });
    }

    private void initView() {
        recycler_result = (RecyclerView) findViewById(R.id.recycler_result);
        recycler_result.setLayoutManager(new LinearLayoutManager(this));
        recycler_result.setHasFixedSize(true);
        resultAdapter = new ResultAdapter(this, this);
        recycler_result.setAdapter(resultAdapter);
        resultAdapter.setData(resultDataList);


    }

    @Override
    public void onCardClick(View view, int position) {

        int idView = view.getId();

        switch (idView) {
            case R.id.booking:
                booking(position);
                break;
            case R.id.nav_map:
                gotToMap(position);
                break;
            case R.id.call:
                callHospital(position);

            default:
                break;

        }
    }

    private void callHospital(int position) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + resultDataList.get(position).getPhone()));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CALL_PHONE},
                    10);
            callHospital(position);
        }else {
            try{
                startActivity(callIntent);
            }
            catch (android.content.ActivityNotFoundException ex){
                Toast.makeText(getApplicationContext(),"Call failed",Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void gotToMap(int position) {
        Destination destination = new Destination();
        destination = resultDataList.get(position).getDestination();
        GoogleMapsNavigation googleMapsNavigation = new GoogleMapsNavigation(this, currentLocation.getLatitude(), currentLocation.getLongitude(), destination.latitude, destination.longitude);
        startActivity(googleMapsNavigation.getGoogleMapsIntentAfterInit());

    }

    private void booking(int position) {
        RequestBooking requestBooking = new RequestBooking();
        requestBooking.subCategoryId = getIntent().getStringExtra("id");
        requestBooking.macAddress = MyMACAdress.getMacAddr();
        requestBooking.phone = "01119955849";
        requestBooking.idHospital = resultDataList.get(position).getId();
        viewModel.booking(requestBooking);


    }
}
