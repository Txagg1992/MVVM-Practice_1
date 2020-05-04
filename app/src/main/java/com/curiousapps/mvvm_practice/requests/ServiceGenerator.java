package com.curiousapps.mvvm_practice.requests;

import com.curiousapps.mvvm_practice.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.curiousapps.mvvm_practice.util.Constants.BASE_URL;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static SchoolApi schoolApi = retrofit.create(SchoolApi.class);

    public static SchoolApi getSchoolApi(){
        return schoolApi;
    }
}
