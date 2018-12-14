package com.example.essam.hospitalscover.webServicse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilterRequest {
    @SerializedName("destination")
    @Expose
    public Destination destination;
    @SerializedName("subCategoryId")
    @Expose
    public String subCategoryId;

    public FilterRequest() {
             destination = new Destination();
    }
}
