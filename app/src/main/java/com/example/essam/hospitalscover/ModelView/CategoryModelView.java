package com.example.essam.hospitalscover.ModelView;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.essam.hospitalscover.Model.Category;
import com.example.essam.hospitalscover.Repository.CategoryRepo;
import com.example.essam.hospitalscover.webServicse.BookingResponse;
import com.example.essam.hospitalscover.webServicse.CheckRequestResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CategoryModelView extends ViewModel {
    private MutableLiveData<Category> allCategory = new MutableLiveData<>();
    private MutableLiveData<CheckRequestResponse> check = new MutableLiveData<>();

    public MutableLiveData<Category> getCategory() {
        return allCategory;
    }

    public MutableLiveData<CheckRequestResponse> getCheck() {
        return check;
    }

    public void getAllCategory(String mac) {
        CategoryRepo.getInstance().getAllCategory(mac).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<Category>() {
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

    public void chechBooking(String mac) {
        CategoryRepo.getInstance().checkingBooking(mac).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<CheckRequestResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CheckRequestResponse value) {
                check.postValue(value);
            }

            @Override
            public void onError(Throwable e) {
                check.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        });

    }
}
