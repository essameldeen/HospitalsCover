package com.example.essam.hospitalscover.Repository;

import com.example.essam.hospitalscover.webServicse.CancelResponse;
import com.example.essam.hospitalscover.webServicse.CanceleRequest;
import com.example.essam.hospitalscover.webServicse.DetalilsWebService;
import com.example.essam.hospitalscover.webServicse.Reservation;

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

public class DetailsRepo {
    private DetalilsWebService detalilsWebService;

    // region singleton implementation
    private static class Loader {
        static DetailsRepo INSTANCE = new DetailsRepo();
    }

    private DetailsRepo() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        String BASE_URL = "https://us-central1-vodafone-hospitals-cover.cloudfunctions.net/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        detalilsWebService = retrofit.create(DetalilsWebService.class);
    }

    public static DetailsRepo getInstance() {
        return DetailsRepo.Loader.INSTANCE;
    }
    // endregion
    // all function that connect to api "end Point "

    public Observable<Boolean> canceleBooking(CanceleRequest reservation) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                detalilsWebService.cancleBooking(reservation).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                        .subscribe(new Observer<CancelResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(CancelResponse value) {
                                if (value != null && value.message.equals("success")) {
                                    emitter.onNext(true);
                                } else {
                                    emitter.onNext(false);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                emitter.onNext(false);
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });


    }

}
