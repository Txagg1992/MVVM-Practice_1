package com.curiousapps.mvvm_practice.activities;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.curiousapps.mvvm_practice.R;
import com.curiousapps.mvvm_practice.adapters.OnSchoolListListener;
import com.curiousapps.mvvm_practice.adapters.SchoolRecyclerViewAdapter;
import com.curiousapps.mvvm_practice.models.SchoolList;
import com.curiousapps.mvvm_practice.util.Testing;
import com.curiousapps.mvvm_practice.viewModels.MainListViewModel;

import java.util.List;

public class MainActivity extends BaseActivity implements OnSchoolListListener {

    private static final String TAG = "MainActivity";

    private MainListViewModel mMainListViewModel;
    private RecyclerView mRecyclerView;
    private SchoolRecyclerViewAdapter mSchoolRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.school_list_recycler_view);

        mMainListViewModel = new ViewModelProvider(this).get(MainListViewModel.class);

        initRecyclerView();
        subscribeObservers();
        testRetrofitReqs();
//        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                testRetrofitReqs();
//            }
//        });
    }

    private void subscribeObservers(){
        mMainListViewModel.getSchoolList().observe(this, new Observer<List<SchoolList>>() {
            @Override
            public void onChanged(List<SchoolList> schoolLists) {
                if (schoolLists != null){
                    Testing.printSchools(TAG, schoolLists);
                    mSchoolRecyclerViewAdapter.setSchoolList(schoolLists);
                }
            }
        });
    }

    private void initRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSchoolRecyclerViewAdapter = new SchoolRecyclerViewAdapter(this);
        mRecyclerView.setAdapter(mSchoolRecyclerViewAdapter);
    }

    private void searchSchoolsApi(int pageNumber) {
        mMainListViewModel.searchSchoolsApi(pageNumber);
    }

    private void testRetrofitReqs() {
        searchSchoolsApi(1);

        //TestClient.getInstance().checkSchoolListRetrofit();

        //TestClient.getInstance().checkSingleItemFromListRetrofit();

        //TestClient.getInstance().checkSchoolSATRetrofit();

    }

    @Override
    public void onSchoolClick(int position) {

    }
}
