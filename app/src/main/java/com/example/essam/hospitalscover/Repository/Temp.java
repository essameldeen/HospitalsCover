package com.example.essam.hospitalscover.Repository;

import android.content.Context;

import com.example.essam.hospitalscover.Model.Example;
import com.example.essam.hospitalscover.webServicse.TempWebService;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Temp {
    private TempWebService tempWebservice;

    // region singleton implementation
    private static class Loader {
        static Temp INSTANCE = new Temp();
    }

    private Temp() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        String BASE_URL = "https://us-central1-vodafone-hospitals-cover.cloudfunctions.net/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        tempWebservice = retrofit.create(TempWebService.class);
    }

    public static Temp getInstance() {
        return Loader.INSTANCE;
    }
    // endregion
    // all function that connect to api "end Point "

    public Observable<Example> getList() {
        return Observable.create(new ObservableOnSubscribe<Example>() {
            @Override
            public void subscribe(ObservableEmitter<Example> emitter) throws Exception {
                tempWebservice.getAll().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<Example>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Example value) {
                        emitter.onNext(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        emitter.onNext(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

            }
        });

    }

}