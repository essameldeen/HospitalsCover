package com.example.essam.hospitalscover.ModelView;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.essam.hospitalscover.Model.Category;
import com.example.essam.hospitalscover.Repository.CategoryRepo;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CategoryModelView extends ViewModel {
    private MutableLiveData<Category> allCategory = new MutableLiveData<>();

    public MutableLiveData<Category> getCategory() {
        return allCategory;
    }


    public void getAllCategory() {
        CategoryRepo.getInstance().getAllCategory().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<Category>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Category value) {
                allCategory.postValue(value);
            }

            @Override
            public void onError(Throwable e) {
                allCategory.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        });

    }
}
