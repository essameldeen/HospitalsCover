package com.example.essam.hospitalscover.webServicse;



import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ResultWebService {
    @POST("Booking-bookResource")
    Observable<BookingResponse> booking(@Body RequestBooking requestBooking);

}
