package com.curiousapps.mvvm_practice.requests;

import com.curiousapps.mvvm_practice.models.SchoolList;
import com.curiousapps.mvvm_practice.models.SchoolSAT;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SchoolApi {

    //Get List of schools limited to 150 schools, and start at
    @GET("resource/s3k6-pzi2.json")
    Call<List<SchoolList>> searchSchools(
            @Query("$$app_token") String app_token,
            @Query("$limit") String limit,
            @Query("$offset") int offset
    );


    //Get Single response for SAT
    @GET("resource/f9bf-2cp4.json")
    Call <List<SchoolSAT>> getSchoolSat(
            @Query("$$app_token") String app_token,
            @Query("dbn") String dbn
            );

    @GET("resource/s3k6-pzi2.json")
    Call<List<SchoolList>> searchOneSchool(
            @Query("$$app_token") String app_token,
            @Query("dbn") String dbn
    );

}
