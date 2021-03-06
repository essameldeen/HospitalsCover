package com.example.essam.hospitalscover.webServicse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Reservation  implements Serializable {

    @SerializedName("createdAt")
    @Expose
    public String createdAt;
    @SerializedName("hospitalResourceId")
    @Expose
    public String hospitalResourceId;
    @SerializedName("macAddress")
    @Expose
    public String macAddress;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("id")
    @Expose
    public String id;


}
