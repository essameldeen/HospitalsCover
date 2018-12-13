package com.example.essam.hospitalscover.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {
    @SerializedName("data")
    @Expose
    private List<CategoryData> data = null;

    public List<CategoryData> getData() {
        return data;
    }

}
