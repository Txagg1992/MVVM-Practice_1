package com.curiousapps.mvvm_practice.util;

public class Gist {

    //        SchoolApi schoolApi = ServiceGenerator.getSchoolApi();
//        Log.d(TAG, "Clicked for API: " + schoolApi);
//        Call<List<SchoolSAT>> responseCall = schoolApi.getSchoolSat(
//                APP_TOKEN,
//                "25Q252"
//        );
//        responseCall.enqueue(new Callback<List<SchoolSAT>>() {
//            @Override
//            public void onResponse(Call<List<SchoolSAT>> call, Response<List<SchoolSAT>> response) {
//                Log.d(TAG, "onResponse: Server: " + response.toString());
//                Log.d(TAG, "<<onResponse>>: Code: " + response.code());
//                if (response.code() == 200) {
//                    Log.d(TAG, "Response: " + response.body());
//                    List<SchoolSAT> schoolSATS = new ArrayList<>(response.body());
//                    for (SchoolSAT schoolSAT : schoolSATS) {
//                        Log.d(TAG, " School Name: " + schoolSAT.getSchoolName());
//                    }
//                } else {
//                    try {
//                        Log.d(TAG, "Error: " + response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<SchoolSAT>> call, Throwable t) {
//
//            }
//        });
//
////        responseCall.enqueue(new Callback<List<SchoolList>>() {
////            @Override
////            public void onResponse(Call<List<SchoolList>> call, Response<List<SchoolList>> response) {
////                Log.d(TAG, "onResponse: Server: " + response.toString());
////                Log.d(TAG, "<<onResponse>>: Code: " + response.code());
////                if (response.code() == 200){
////                    Log.d(TAG, "Response: " + response.body());
////                    List<SchoolList> schoolLists = new ArrayList<>(response.body());
////                    int i = 0;
////                    for (SchoolList schoolList: schoolLists){
////                        i = i +1;
////                        Log.d(TAG, i + " School Name: " + schoolList.getSchool_name());
////                    }
////                }else {
////                    try {
////                        Log.d(TAG, "Error: " + response.errorBody().string());
////                    }catch (IOException ex){
////                        ex.printStackTrace();
////                    }
////                }
////            }
////
////            @Override
////            public void onFailure(Call<List<SchoolList>> call, Throwable t) {
////
////            }
////        });

}
