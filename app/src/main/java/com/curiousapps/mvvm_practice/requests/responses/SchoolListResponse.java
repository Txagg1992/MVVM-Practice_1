package com.curiousapps.mvvm_practice.requests.responses;

import com.curiousapps.mvvm_practice.models.SchoolList;

import java.util.List;

public class SchoolListResponse {

    private List<SchoolList> schoolList;

    public List<SchoolList> getSchoolList(){
        return schoolList;
    }

    @Override
    public String toString() {
        return "SchoolListResponse{" +
                "schoolList=" + schoolList +
                '}';
    }
}
