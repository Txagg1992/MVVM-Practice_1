package com.curiousapps.mvvm_practice.requests.responses;

import com.curiousapps.mvvm_practice.models.SchoolSAT;

import java.util.List;

public class SchoolSatResponse {

    private SchoolSAT schoolSAT;

    public SchoolSAT getSchoolSATS(){
        return schoolSAT;
    }

    @Override
    public String toString() {
        return "SchoolSatResponse{" +
                "schoolSATS=" + schoolSAT +
                '}';
    }
}
