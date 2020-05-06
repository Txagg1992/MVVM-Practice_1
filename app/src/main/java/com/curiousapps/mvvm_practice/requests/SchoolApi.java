package com.curiousapps.mvvm_practice.requests;

import com.curiousapps.mvvm_practice.models.SchoolList;
import com.curiousapps.mvvm_practice.models.SchoolSAT;
import com.curiousapps.mvvm_practice.models.PicObject;
import com.curiousapps.mvvm_practice.requests.responses.SchoolListResponse;
import com.curiousapps.mvvm_practice.requests.responses.SchoolSatResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SchoolApi {

    //Get List of schools limited to 150 schools, and 20 per page
    @GET("resource/s3k6-pzi2.json")
    Call<List<SchoolList>> searchSchools(
            @Query("$$app_token") String app_token,
            @Query("$limit") String limit,
            @Query("$offset") String offset
    );
    @GET("resource/s3k6-pzi2.json")
    Call<SchoolListResponse> searchSchoolsList(
            @Query("$$app_token") String app_token,
            @Query("$limit") String limit,
            @Query("$offset") String offset
    );

    //Get Single response for SAT
    @GET("resource/f9bf-2cp4.json")
    Call <List<SchoolSAT>> getSchoolSat(
            @Query("$$app_token") String app_token,
            @Query("dbn") String dbn
            );

    @GET("users")
    Call<List<PicObject>> getUserName();

    @GET("resource/s3k6-pzi2.json")
    Call<List<SchoolList>> searchOneSchool(
            @Query("$$app_token") String app_token,
            @Query("dbn") String dbn
    );

}
