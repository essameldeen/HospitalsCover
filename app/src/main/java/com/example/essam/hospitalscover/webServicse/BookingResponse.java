package com.example.essam.hospitalscover.webServicse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BookingResponse implements Serializable {
    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName("available")
    @Expose
    public Boolean available;


}
