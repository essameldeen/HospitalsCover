package com.example.essam.hospitalscover.ModelView;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.essam.hospitalscover.Model.Example;
import com.example.essam.hospitalscover.Repository.Temp;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TempModelView extends ViewModel {

    private MutableLiveData<Example> test = new MutableLiveData<>();

    public MutableLiveData<Example> getTest() {
        return test;
    }

    // all function that connect between the view and repo :D
    public void getAllTest(  ) {

        Temp.getInstance().getList().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<Example>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Example value) {
                test.postValue(value);
            }

            @Override
            public void onError(Throwable e) {
                test.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        });

    }
}
