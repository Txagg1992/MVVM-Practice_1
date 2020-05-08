package com.curiousapps.mvvm_practice.repositories;

import android.accounts.NetworkErrorException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.curiousapps.mvvm_practice.models.SchoolList;
import com.curiousapps.mvvm_practice.requests.SchoolApiClient;

import java.util.List;

import static com.curiousapps.mvvm_practice.util.Constants.OFFSET;
import static com.curiousapps.mvvm_practice.util.Constants.OFFSET2;

public class SchoolRepository {

    private static SchoolRepository instance;
    private SchoolApiClient mSchoolApiClient;
    private int mOffset;
    private int mLimit;

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

    public void searchSchoolsApi(int limit, int offset){
        if (offset == 0){
            offset = 1;
        }
        mLimit = limit;
        mOffset = offset;
        mSchoolApiClient.searchSchoolsApi(offset);
    }

    public void searchNextPage(){
        searchSchoolsApi(mLimit, mOffset + 19);
    }

    public void cancelRequest(){
        mSchoolApiClient.cancelRequest();
    }

}
