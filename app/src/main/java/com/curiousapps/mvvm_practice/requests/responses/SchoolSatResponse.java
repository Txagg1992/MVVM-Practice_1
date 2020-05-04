package com.curiousapps.mvvm_practice.requests.responses;

import com.curiousapps.mvvm_practice.models.SchoolSAT;

import java.util.List;

public class SchoolSatResponse {

    private List<SchoolSAT> schoolSATS;

    public List<SchoolSAT> getSchoolSATS(){
        return schoolSATS;
    }

    @Override
    public String toString() {
        return "SchoolSatResponse{" +
                "schoolSATS=" + schoolSATS +
                '}';
    }
}
