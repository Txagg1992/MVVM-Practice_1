package com.curiousapps.mvvm_practice.repositories;

import android.accounts.NetworkErrorException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.curiousapps.mvvm_practice.models.SchoolList;
import com.curiousapps.mvvm_practice.requests.SchoolApiClient;

import java.util.List;

public class SchoolRepository {

    private static SchoolRepository instance;
    private SchoolApiClient mSchoolApiClient;

    public static SchoolRepository getInstance(){
        if (instance == null){
            instance = new SchoolRepository();
        }
        return instance;
    }

    private SchoolRepository() {
        mSchoolApiClient = SchoolApiClient.getInstance();
    }
    public LiveData<List<SchoolList>> getSchoolList(){
        return mSchoolApiClient.getSchoolList();
    }

    public void searchSchoolsApi(int pageNumber){
        if (pageNumber == 0){
            pageNumber = 1;
        }
        mSchoolApiClient.searchSchoolsApi(pageNumber);
    }

}
