package com.example.essam.hospitalscover.webServicse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CheckRequestResponse implements Serializable {
    @SerializedName("data")
    @Expose
    public InformationBooking data;
    @SerializedName("redirect")
    @Expose
    public Boolean redirect;
}
