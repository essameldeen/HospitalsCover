package com.example.essam.hospitalscover.ModelView;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.essam.hospitalscover.Repository.Temp;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TempModelView extends ViewModel {

    private MutableLiveData<List<String>> test = new MutableLiveData<>();

    public MutableLiveData<List<String>> getTest() {
        return test;
    }

    // all function that connect between the view and repo :D
    public void getAllTest(Context context, String id) {

        Temp.getInstance().getList(id, context).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> value) {
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
