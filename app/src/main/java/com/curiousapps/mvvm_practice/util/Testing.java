package com.curiousapps.mvvm_practice.util;

import android.util.Log;

import com.curiousapps.mvvm_practice.models.SchoolList;

import java.util.List;

public class Testing {
    private static int i = 0;
    public static void printSchools(String tag, List<SchoolList> lists){
        for (SchoolList schoolList: lists){
            i+=1;
            Log.d(tag + i, "<<SchoolList.onChanged>>" + schoolList.getSchool_name());
            Log.d(tag, "<<SchoolList.onChanged>>" + schoolList.getCity());
        }
    }
}
