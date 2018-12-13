package com.example.essam.hospitalscover.ModelView;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.essam.hospitalscover.Model.Result;

public class ResultViewModel extends ViewModel {
    private MutableLiveData<Result> result = new MutableLiveData<>();

    public MutableLiveData<Result> getResult() {
        return result;
    }


}
