package com.curiousapps.mvvm_practice.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.curiousapps.mvvm_practice.R;
import com.curiousapps.mvvm_practice.models.SchoolList;
import com.curiousapps.mvvm_practice.models.SchoolSAT;
import com.curiousapps.mvvm_practice.models.TypiCode;
import com.curiousapps.mvvm_practice.requests.SchoolApi;
import com.curiousapps.mvvm_practice.requests.ServiceGenerator;
import com.curiousapps.mvvm_practice.requests.TestClient;
import com.curiousapps.mvvm_practice.requests.responses.SchoolSatResponse;
import com.curiousapps.mvvm_practice.util.Constants;
import com.curiousapps.mvvm_practice.util.Testing;
import com.curiousapps.mvvm_practice.viewModels.MainListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.curiousapps.mvvm_practice.util.Constants.*;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private MainListViewModel mMainListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainListViewModel = new ViewModelProvider(this).get(MainListViewModel.class);

        //subscribeObservers();
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitReqs();
            }
        });
    }

    private void subscribeObservers(){
//        mMainListViewModel.getSchoolList().observe(this, new Observer<List<SchoolList>>() {
//            @Override
//            public void onChanged(List<SchoolList> schoolLists) {
//                Log.d(TAG , "SHITTTTTTT");
//                if (schoolLists != null){
//                    //Testing.printSchools("SchoolTest", schoolLists);
//                }
//            }
//        });
    }

    private void searchSchoolsApi(int pageNumber) {

        // mMainListViewModel.searchSchoolsApi(pageNumber);
    }

    private void testRetrofitReqs() {
        searchSchoolsApi(1);

        TestClient.getInstance().checkListRetrofit();

        //TestClient.getInstance().checkDetailRetrofit();

    }
}
