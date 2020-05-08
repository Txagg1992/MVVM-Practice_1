package com.curiousapps.mvvm_practice.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.curiousapps.mvvm_practice.models.SchoolList;
import com.curiousapps.mvvm_practice.models.SchoolSAT;
import com.curiousapps.mvvm_practice.requests.responses.SchoolListResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.curiousapps.mvvm_practice.util.Constants.APP_TOKEN;
import static com.curiousapps.mvvm_practice.util.Constants.LIMIT;
import static com.curiousapps.mvvm_practice.util.Constants.OFFSET;
import static com.curiousapps.mvvm_practice.util.Constants.PER_PAGE;

public class TestClient {
    private static final String TAG = "TestClient";
    private static TestClient instance;

    public static TestClient getInstance() {
        if (instance == null) {
            instance = new TestClient();
        }
        return instance;
    }

    public void checkSingleItemFromListRetrofit() {
        SchoolApi schoolApi = ServiceGenerator.getSchoolApi();
        Log.d(TAG, "Clicked for SingleSchoolApi:" + schoolApi);
        Call<List<SchoolList>> responseCall = schoolApi.searchOneSchool(
                APP_TOKEN,
                "25Q252"
        );
        responseCall.enqueue(new Callback<List<SchoolList>>() {
            @Override
            public void onResponse(Call<List<SchoolList>> call, Response<List<SchoolList>> response) {
                Log.d(TAG, "onResponse: Server: " + response.toString());
                Log.d(TAG, "<<onResponse>>: Code: " + response.code());
                if (response.code() == 200) {
                    Log.d(TAG, "Response: " + response.body());
                    List<SchoolList> schoolLists = new ArrayList<>(response.body());
                    for (SchoolList schoolList : schoolLists) {
                        Log.d(TAG, " School Dbn: " + schoolList.getDbn());
                        Log.d(TAG, " School Name: " + schoolList.getSchool_name());
                    }

                } else {
                    try {
                        Log.d(TAG, "Error: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SchoolList>> call, Throwable t) {

            }
        });
    }

//    public void checkSchoolListResponse(){
//        SchoolApi schoolApi = ServiceGenerator.getSchoolApi();
//        Log.d(TAG, "Clicked for ApiSchoolListResponse" + schoolApi);
//        Call<SchoolListResponse> responseCall = schoolApi.searchSchoolsList(
//                APP_TOKEN,
//                LIMIT,
//                PER_PAGE
//        );
//        responseCall.enqueue(new Callback<SchoolListResponse>() {
//            @Override
//            public void onResponse(Call<SchoolListResponse> call, Response<SchoolListResponse> response) {
//                Log.d(TAG, "OnResponse: Server: " + response.toString());
//                Log.d(TAG, "<<onResponse>>: Code: " + response.code());
//                if (response.code() == 200){
//                    Log.d(TAG, "Response body" + response.body());
//                    List<SchoolList> schoolLists = new ArrayList<>(response.body().getSchoolList());
//                    for (SchoolList schoolList: schoolLists){
//                        Log.d(TAG, " SchoolName: " + schoolList.getSchool_name());
//                    }
//                }else {
//                    try {
//                        Log.d(TAG, "Response Error: " + response.errorBody().string());
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SchoolListResponse> call, Throwable t) {
//
//            }
//        });
//    }
    public void checkSchoolListRetrofit() {
        SchoolApi schoolApi = ServiceGenerator.getSchoolApi();
        Log.d(TAG, "Clicked for ListApi: " + schoolApi);
        Call<List<SchoolList>> responseCall = schoolApi.searchSchools(
                APP_TOKEN,
                LIMIT,
                OFFSET
        );

        responseCall.enqueue(new Callback<List<SchoolList>>() {
            @Override
            public void onResponse(Call<List<SchoolList>> call, Response<List<SchoolList>> response) {
                Log.d(TAG, "OnResponse: Server: " + response.toString());
                Log.d(TAG, "<<onResponse>>: Code: " + response.code());
                if (response.code() == 200) {
                    Log.d(TAG, "Response Body" + response.body());
                    List<SchoolList> schoolLists = new ArrayList<>(response.body());
                    for (SchoolList schoolList : schoolLists) {
                        Log.d(TAG, " SchoolName: " + schoolList.getSchool_name());
                    }
                } else {
                    try {
                        Log.d(TAG, "Response Error: " + response.errorBody().string());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SchoolList>> call, Throwable t) {

            }
        });

    }

    public void checkSchoolSATRetrofit() {
        SchoolApi schoolApi = ServiceGenerator.getSchoolApi();
        Log.d(TAG, "Clicked for SatAPI: " + schoolApi);
        Call<List<SchoolSAT>> responseCall = schoolApi.getSchoolSat(
                APP_TOKEN,
                "25Q252"
        );
        responseCall.enqueue(new Callback<List<SchoolSAT>>() {
            @Override
            public void onResponse(Call<List<SchoolSAT>> call, Response<List<SchoolSAT>> response) {
                Log.d(TAG, "onResponse: Server: " + response.toString());
                Log.d(TAG, "<<onResponse>>: Code: " + response.code());
                if (response.code() == 200) {
                    Log.d(TAG, "Response: " + response.body());
                    List<SchoolSAT> schoolSATS = new ArrayList<>(response.body());
                    for (SchoolSAT schoolSAT : schoolSATS) {
                        Log.d(TAG, " School Dbn: " + schoolSAT.getDbn());
                        Log.d(TAG, " School Name: " + schoolSAT.getSchool_Name());
                    }
                } else {
                    try {
                        Log.d(TAG, "Error: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SchoolSAT>> call, Throwable t) {

            }
        });

    }
}
