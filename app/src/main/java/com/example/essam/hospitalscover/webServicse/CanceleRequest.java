package com.example.essam.hospitalscover.webServicse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CanceleRequest {
    @SerializedName("reservation")
    @Expose
    public Reservation reservation;


}
