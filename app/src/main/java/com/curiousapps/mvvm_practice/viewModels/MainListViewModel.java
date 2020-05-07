package com.curiousapps.mvvm_practice.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.curiousapps.mvvm_practice.models.SchoolList;
import com.curiousapps.mvvm_practice.repositories.SchoolRepository;

import java.util.List;

public class MainListViewModel extends ViewModel {

    private SchoolRepository mSchoolRepository;

    public MainListViewModel() {
        mSchoolRepository = SchoolRepository.getInstance();
    }

    public LiveData<List<SchoolList>> getSchoolList() {
        return mSchoolRepository.getSchoolList();
    }

    public void searchSchoolsApi(int pageNumber) {
        mSchoolRepository.searchSchoolsApi(pageNumber);
    }

    public boolean onBackPressed(){
        return true;
    }
}
