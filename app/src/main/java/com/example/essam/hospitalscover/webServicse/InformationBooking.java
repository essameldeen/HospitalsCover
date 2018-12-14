package com.example.essam.hospitalscover.webServicse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InformationBooking implements Serializable {
    @SerializedName("reservation")
    @Expose
    public Reservation reservation;
    @SerializedName("hospitalName")
    @Expose
    public String hospitalName;
    @SerializedName("hospitalAddress")
    @Expose
    public String hospitalAddress;
    @SerializedName("hospitalLatitude")
    @Expose
    public Double hospitalLatitude;
    @SerializedName("hospitalLongitude")
    @Expose
    public Double hospitalLongitude;
    @SerializedName("hospitalPhone")
    @Expose
    public String hospitalPhone;
    @SerializedName("hospitalRating")
    @Expose
    public Double hospitalRating;
    @SerializedName("hospitalWorkingHours")
    @Expose
    public String hospitalWorkingHours;
    @SerializedName("hospitalId")
    @Expose
    public String hospitalId;
    @SerializedName("subCategoryName")
    @Expose
    public String subCategoryName;
    @SerializedName("categoryIcon")
    @Expose
    public String categoryIcon;
    @SerializedName("hospitalIcon")
    @Expose
    public String hospitalIcon;
}
