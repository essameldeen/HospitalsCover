package com.example.essam.hospitalscover.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.essam.hospitalscover.webServicse.Destination;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public  class ResultData implements Serializable {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("destination")
    @Expose
    private Destination destination;
    @SerializedName("workingHours")
    @Expose
    private String workingHours;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("distance")
    @Expose
    private Double distance;

    public ResultData() {
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getRating() {

        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }




}
