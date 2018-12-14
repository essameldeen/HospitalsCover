package com.example.essam.hospitalscover.Repository;

import com.example.essam.hospitalscover.webServicse.BookingResponse;
import com.example.essam.hospitalscover.webServicse.RequestBooking;
import com.example.essam.hospitalscover.webServicse.ResultWebService;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultRepo {
    private ResultWebService resultWebService;

    // region singleton implementation
    private static class Loader {
        static ResultRepo INSTANCE = new ResultRepo();
    }

    private ResultRepo() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        String BASE_URL = "https://us-central1-vodafone-hospitals-cover.cloudfunctions.net/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        resultWebService = retrofit.create(ResultWebService.class);
    }

    public static ResultRepo getInstance() {
        return ResultRepo.Loader.INSTANCE;
    }
    // endregion
    // all function that connect to api "end Point "

    public Observable<BookingResponse> booking(RequestBooking requestBooking) {
        return Observable.create(new ObservableOnSubscribe<BookingResponse>() {
            @Override
            public void subscribe(ObservableEmitter<BookingResponse> emitter) throws Exception {
                resultWebService.booking(requestBooking).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<BookingResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BookingResponse value) {
                        emitter.onNext(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
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
