package com.curiousapps.mvvm_practice.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.curiousapps.mvvm_practice.models.SchoolList;
import com.curiousapps.mvvm_practice.requests.SchoolApi;
import com.curiousapps.mvvm_practice.requests.TestClient;

import java.util.List;

public class TestViewModel {

    private static TestViewModel instance;
    private MutableLiveData<List<SchoolList>> mSchoolLists;
    private TestClient mTestClient;

    public static TestViewModel getInstance(){
        if (instance == null){
            instance = new TestViewModel();
        }
        return instance;
    }

    private TestViewModel(){
        mSchoolLists = new MutableLiveData<>();
    }

    public LiveData<List<SchoolList>> getSchoolLists(){
        return mSchoolLists;
    }
}
