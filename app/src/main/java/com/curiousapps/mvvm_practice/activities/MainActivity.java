package com.curiousapps.mvvm_practice.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.curiousapps.mvvm_practice.R;
import com.curiousapps.mvvm_practice.models.SchoolList;
import com.curiousapps.mvvm_practice.models.SchoolSAT;
import com.curiousapps.mvvm_practice.models.TypiCode;
import com.curiousapps.mvvm_practice.requests.SchoolApi;
import com.curiousapps.mvvm_practice.requests.ServiceGenerator;
import com.curiousapps.mvvm_practice.util.Constants;
import com.curiousapps.mvvm_practice.util.Testing;
import com.curiousapps.mvvm_practice.viewModels.MainListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.curiousapps.mvvm_practice.util.Constants.*;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private MainListViewModel mMainListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainListViewModel = new ViewModelProvider(this).get(MainListViewModel.class);

        //subscribeObservers();
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitReqs();
            }
        });
    }

//    private void subscribeObservers(){
//        mMainListViewModel.getSchoolList().observe(this, new Observer<List<SchoolList>>() {
//            @Override
//            public void onChanged(List<SchoolList> schoolLists) {
//                Log.d(TAG , "SHITTTTTTT");
//                if (schoolLists != null){
//                    //Testing.printSchools("SchoolTest", schoolLists);
//                }
//            }
//        });
//    }

    private void searchSchoolsApi(int pageNumber) {

       // mMainListViewModel.searchSchoolsApi(pageNumber);
    }

    private void testRetrofitReqs() {
        //searchSchoolsApi(1);

//        SchoolApi schoolApi = ServiceGenerator.getSchoolApi();
//        Log.d(TAG, "Clicked for Api: " + schoolApi);
//        Call<List<SchoolList>> responseCall = schoolApi.searchSchools(
//                APP_TOKEN,
//                LIMIT,
//                PER_PAGE
//        );
//
//        responseCall.enqueue(new Callback<List<SchoolList>>() {
//            @Override
//            public void onResponse(Call<List<SchoolList>> call, Response<List<SchoolList>> response) {
//                Log.d(TAG, "OnResponse: Server: " + response.toString());
//                Log.d(TAG, "<<onResponse>>: Code: " + response.code());
//                if (response.code() == 200){
//                    Log.d(TAG, "Response Body" + response.body());
//                    List<SchoolList> schoolLists = new ArrayList<>(response.body());
//                    for (SchoolList schoolList: schoolLists){
//                        Log.d(TAG, " SchoolName: " + schoolList.getSchool_name());
//                    }
//                }else {
//                    try {
//                        Log.d(TAG, "Response Error: " + response.errorBody().string());
//                    }catch (IOException ex){
//                        ex.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<SchoolList>> call, Throwable t) {
//
//            }
//        });

        SchoolApi schoolApi = ServiceGenerator.getSchoolApi();
        Log.d(TAG, "Clicked for API: " + schoolApi);
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
                        Log.d(TAG, " School Name: " + schoolSAT.getDbn());
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
