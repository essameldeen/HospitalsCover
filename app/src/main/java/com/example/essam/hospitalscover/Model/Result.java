package com.example.essam.hospitalscover.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("data")
    @Expose
    private List<ResultData> data = null;

    public List<ResultData> getData() {
        return data;
    }

    public void setData(List<ResultData> data) {
        this.data = data;
    }
}
