package com.example.essam.hospitalscover.Repository;

import com.example.essam.hospitalscover.Model.Category;
import com.example.essam.hospitalscover.webServicse.CategoryWebService;

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

public class SubCategoryRepo {
    private CategoryWebService categoryWebService;

    // region singleton implementation
    private static class Loader {
        static SubCategoryRepo INSTANCE = new SubCategoryRepo();
    }

    private SubCategoryRepo() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        String BASE_URL = "https://us-central1-vodafone-hospitals-cover.cloudfunctions.net/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        categoryWebService = retrofit.create(CategoryWebService.class);
    }

    public static SubCategoryRepo getInstance() {
        return SubCategoryRepo.Loader.INSTANCE;
    }
    // endregion
    // all function that connect to api "end Point "


    public Observable<Category> getAllCategory() {
        return Observable.create(new ObservableOnSubscribe<Category>() {
            @Override
            public void subscribe(ObservableEmitter<Category> emitter) throws Exception {
                categoryWebService.getAllCategory().subscribeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(new Observer<Category>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Category value) {
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
