package com.example.essam.hospitalscover.webServicse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Destination implements Serializable {
    @SerializedName("longitude")
    @Expose
    public Double longitude;
    @SerializedName("latitude")
    @Expose
    public Double latitude;

    public Destination() {
    }
}
