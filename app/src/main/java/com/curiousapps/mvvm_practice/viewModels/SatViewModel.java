package com.curiousapps.mvvm_practice.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.curiousapps.mvvm_practice.models.SchoolSAT;
import com.curiousapps.mvvm_practice.repositories.SchoolRepository;

import java.util.List;

public class SatViewModel extends ViewModel {
    private SchoolRepository mSchoolRepository;
    private String mDBn;

    public SatViewModel(){
        mSchoolRepository = SchoolRepository.getInstance();
    }

    public LiveData<List<SchoolSAT>> getSchoolSat(){
        return mSchoolRepository.getSchoolSat();
    }

    public void searchSchoolSat(String dbn){
        mDBn = dbn;
        mSchoolRepository.searchSchoolSATApi(dbn);
    }
}
