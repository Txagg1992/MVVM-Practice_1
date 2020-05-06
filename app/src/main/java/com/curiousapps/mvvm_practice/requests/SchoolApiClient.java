package com.curiousapps.mvvm_practice.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.curiousapps.mvvm_practice.AppExecutors;
import com.curiousapps.mvvm_practice.models.SchoolList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.curiousapps.mvvm_practice.util.Constants.APP_TOKEN;
import static com.curiousapps.mvvm_practice.util.Constants.LIMIT;
import static com.curiousapps.mvvm_practice.util.Constants.NETWORK_TIMEOUT;
import static com.curiousapps.mvvm_practice.util.Constants.PER_PAGE;

public class SchoolApiClient {

    private static final String TAG = "SchoolApiClient";
    private static SchoolApiClient instance;
    private MutableLiveData<List<SchoolList>> mSchoolList;

    private RetrieveSchoolListRunnable mRetrieveSchoolListRunnable;


    public static SchoolApiClient getInstance(){
        if (instance == null){
            instance = new SchoolApiClient();
        }
        return instance;
    }

    private SchoolApiClient(){
        mSchoolList = new MutableLiveData<>();
    }

    public LiveData<List<SchoolList>> getSchoolList(){
        return mSchoolList;
    }

    public void searchSchoolsApi(int pageNumber){
        if (mRetrieveSchoolListRunnable != null){
            mRetrieveSchoolListRunnable = null;
        }
        mRetrieveSchoolListRunnable = new RetrieveSchoolListRunnable(pageNumber);
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveSchoolListRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Let user know The network timed out
               handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveSchoolListRunnable implements Runnable{

        private int pageNumber;
        boolean cancelRequest;

        public RetrieveSchoolListRunnable(int pageNumber) {
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {

                //TestClient.getInstance().checkSchoolListRetrofit();
                Response<List<SchoolList>> response = getSchools(pageNumber).execute();
                if (cancelRequest){
                    return;
                }
                if (response.code() == 200){
                    List<SchoolList> schoolLists = new ArrayList<>(response.body());
                    if (pageNumber == 1){
                        mSchoolList.postValue(schoolLists);
                    }else {
                        List<SchoolList> currentSchools = mSchoolList.getValue();
                        currentSchools.add((SchoolList) schoolLists);
                        mSchoolList.postValue(currentSchools);
                    }
                }else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "runError: " + error);
                    mSchoolList.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mSchoolList.postValue(null);
            }

        }

        private Call<List<SchoolList>> getSchools(int pageNumber){
            return ServiceGenerator.getSchoolApi().searchSchools(
              APP_TOKEN,
              LIMIT,
              PER_PAGE
            );
        }

        private void cancelRequest(){
            Log.d(TAG, "CancelRequest: cancelling search request");
            cancelRequest = true;
        }
    }
//
//    private static final String TAG = "SchoolApiClient";
//    private static SchoolApiClient instance;
//    private MutableLiveData<List<SchoolList>> mSchools;
//    private RetrieveSchoolsRunnable mRetrieveSchoolsRunnable;
//
//    public static SchoolApiClient getInstance(){
//        if (instance == null){
//            instance = new SchoolApiClient();
//        }
//        return instance;
//    }
//
//    private SchoolApiClient(){
//        mSchools = new MutableLiveData<>();
//    }
//    public LiveData<List<SchoolList>> getSchools(){
//        return mSchools;
//    }
//
//    public void searchSchoolsApi(int pageNumber){
//        if (mRetrieveSchoolsRunnable != null){
//            mRetrieveSchoolsRunnable = null;
//        }
//        mRetrieveSchoolsRunnable = new RetrieveSchoolsRunnable(pageNumber);
//        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveSchoolsRunnable);
//        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
//            @Override
//            public void run() {
//                handler.cancel(true);
//            }
//        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
//    }
//
//    private class RetrieveSchoolsRunnable implements Runnable{
//
//        private int pageNumber;
//        boolean cancelRequest;
//
//        public RetrieveSchoolsRunnable(int pageNumber) {
//            this.pageNumber = pageNumber;
//            cancelRequest = false;
//        }
//
//        @Override
//        public void run() {
//            try {
//                Response response = getSchools(pageNumber).execute();
//                Log.d(TAG, "Response Code: " + response.code());
//                if (cancelRequest){
//                    return;
//                }
//
//                if (response.code() == 200){
//                    Log.d(TAG, "Response: " + response.body());
//                    List<SchoolList> schoolLists = new ArrayList<>(((SchoolListResponse)response.body()).getSchoolList());
//                    Log.d(TAG, schoolLists.toString());
//                    if (pageNumber == 1){
//                        mSchools.postValue(schoolLists);
//                        Log.d(TAG, "Listing..." + schoolLists);
//                    }else {
//                        List<SchoolList> currentSchools = mSchools.getValue();
//                        currentSchools.addAll(schoolLists);
//                        mSchools.postValue(currentSchools);
//                    }
//                }else {
//                    String error = response.errorBody().string();
//                    Log.e(TAG, "schoolRunnable: " + error);
//                    mSchools.postValue(null);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                mSchools.postValue(null);
//            }
//
//        }
//        private Call<List<SchoolList>> getSchools(int pageNumber) {
//            return ServiceGenerator.getSchoolApi().searchSchools(
//                    APP_TOKEN,
//                    LIMIT,
//                    PER_PAGE
//            );
//        }
//        private void cancelRequest(){
//            Log.d(TAG, "cancelRequest: Stop the Presses!");
//            cancelRequest = true;
//        }
//    }
}

