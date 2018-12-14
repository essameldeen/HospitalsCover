package com.example.essam.hospitalscover.ModelView;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.essam.hospitalscover.Repository.DetailsRepo;
import com.example.essam.hospitalscover.webServicse.CanceleRequest;
import com.example.essam.hospitalscover.webServicse.Reservation;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailsViewModel extends ViewModel {
    private MutableLiveData<Boolean> cancele = new MutableLiveData<>();

    public MutableLiveData<Boolean> getCancele() {
        return cancele;
    }

    public void canceleBooking(CanceleRequest reservation) {
        DetailsRepo.getInstance().canceleBooking(reservation).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean value) {
                        cancele.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        cancele.postValue(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
