package com.example.essam.hospitalscover.webServicse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("reservation")
    @Expose
    public Reservation reservation;
    @SerializedName("hospital")
    @Expose
    public Hospital hospital;



}