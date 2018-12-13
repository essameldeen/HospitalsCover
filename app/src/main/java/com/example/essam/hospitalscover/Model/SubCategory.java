package com.example.essam.hospitalscover.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategory {

    @SerializedName("data")
    @Expose
    private List<SubCategoryData> data = null;

    public List<SubCategoryData> getData() {
        return data;
    }

    public void setData(List<SubCategoryData> data) {
        this.data = data;
    }

}