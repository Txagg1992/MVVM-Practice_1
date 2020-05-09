package com.curiousapps.mvvm_practice.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.curiousapps.mvvm_practice.models.SchoolList;
import com.curiousapps.mvvm_practice.requests.SchoolApiClient;
import com.curiousapps.mvvm_practice.util.Constants;

import java.util.List;

public class SchoolRepository {

    private static SchoolRepository instance;
    private SchoolApiClient mSchoolApiClient;
    private int mOffset;
    private int mLimit;
    private MutableLiveData<Boolean> mIsQueryExhausted = new MutableLiveData<>();
    private MediatorLiveData<List<SchoolList>> mSchoolList = new MediatorLiveData<>();

    public static SchoolRepository getInstance(){
        if (instance == null){
            instance = new SchoolRepository();
        }
        return instance;
    }

    private SchoolRepository() {
        mSchoolApiClient = SchoolApiClient.getInstance();
        initMediators();
    }
    private void initMediators(){
        LiveData<List<SchoolList>> schoolListApiSource = mSchoolApiClient.getSchoolList();
        mSchoolList.addSource(schoolListApiSource, new Observer<List<SchoolList>>() {
            @Override
            public void onChanged(List<SchoolList> schoolLists) {
                if (schoolLists != null){
                    mSchoolList.setValue(schoolLists);
                    doneQuery(schoolLists);
                }else {
                    //search database
                    doneQuery(null);
                }
            }
        });
    }

    private void doneQuery(List<SchoolList> list){
        if (list != null){
            if (list.size() % 10 != 0){
                mIsQueryExhausted.setValue(true);
            }
        }else {
            mIsQueryExhausted.setValue(true);
        }
    }

    public LiveData<Boolean> isQueryExhausted(){
        return mIsQueryExhausted;
    }

    public LiveData<List<SchoolList>> getSchoolList(){
        return mSchoolList;
    }
    public LiveData<List<SchoolList>> getSchool(){
        return mSchoolApiClient.getSchool();
    }
    public LiveData<Boolean> isSchoolRequestTimedOut() {
        return mSchoolApiClient.isSchoolRequestTimedOut();
    }

    public void searchSingleSchoolApi(String dbn){
        mSchoolApiClient.searchSingleSchoolApi(dbn);
    }

    public void searchSchoolsApi(int limit, int offset){
        if (offset == 0){
            offset = 1;
        }
        mLimit = limit;
        mOffset = offset;
        mIsQueryExhausted.setValue(false);
        mSchoolApiClient.searchSchoolsApi(offset);
    }

    public void searchNextPage(){
        searchSchoolsApi(mLimit, mOffset + 19);
    }

    public void cancelRequest(){
        mSchoolApiClient.cancelRequest();
    }

}
