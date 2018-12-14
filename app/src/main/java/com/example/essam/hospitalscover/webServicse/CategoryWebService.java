package com.example.essam.hospitalscover.webServicse;


import com.example.essam.hospitalscover.Model.Category;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface CategoryWebService {

    @GET("Categories-getCategories")
    Observable<Category> getAllCategory(@Header("Authorization") String mac);

    @GET("User-checkBooking")
    Observable<CheckRequestResponse> checkBooking(@Header("Authorization") String mac);
}
