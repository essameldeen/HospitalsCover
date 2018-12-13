package com.example.essam.hospitalscover.ModelView;

import android.arch.lifecycle.MutableLiveData;
import com.example.essam.hospitalscover.Model.SubCategory;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SubCategoryModelViewextends ViewModel {
    private MutableLiveData<SubCategory> allSubCategory = new MutableLiveData<>();

    public MutableLiveData<SubCategory> getSubCategory() {
        return allSubCategory;
    }


    public void getAllSubCategory() {
        SubCategoryRepo.getInstance().getAllSubCategory().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<SubCategory>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SubCategory value) {
                allSubCategory.postValue(value);
            }

            @Override
            public void onError(Throwable e) {
                allSubCategory.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        });

    }
}
