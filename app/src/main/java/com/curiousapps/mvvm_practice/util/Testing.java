package com.curiousapps.mvvm_practice.util;

import android.util.Log;

import com.curiousapps.mvvm_practice.models.SchoolList;

import java.util.List;

public class Testing {

    public static void printSchools(String tag, List<SchoolList> lists){
        for (SchoolList schoolList: lists){
            Log.d(tag, "<<SchoolList.onChanged>>" + schoolList.getSchool_name());
            Log.d(tag, "<<SchoolList.onChanged>>" + schoolList.getCity());
        }
    }
}
