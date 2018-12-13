package com.example.essam.hospitalscover.webServicse;

import com.example.essam.hospitalscover.Model.Category;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SubCategoryWebService {
    @GET("Categories-getSubCategories/{categoryId}")
    Observable<Category> getAllSubCategory(@Path("categoryId") String id);


}
