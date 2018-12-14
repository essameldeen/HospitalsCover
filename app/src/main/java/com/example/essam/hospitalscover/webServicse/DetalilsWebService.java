package com.example.essam.hospitalscover.webServicse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DetalilsWebService {
    @POST("Booking-cancelRequiest")
    Observable<CancelResponse> cancleBooking(@Body CanceleRequest reservation);
}
