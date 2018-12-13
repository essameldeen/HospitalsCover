package com.example.essam.hospitalscover.webServicse;

import com.example.essam.hospitalscover.Model.Example;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TempWebService {
    // all declaration function that used to connect to end point

    @GET("testOnAndroid")
    Observable<Example> getAll( );
}
