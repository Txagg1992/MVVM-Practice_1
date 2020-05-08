package com.curiousapps.mvvm_practice.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.curiousapps.mvvm_practice.R;
import com.curiousapps.mvvm_practice.models.SchoolList;
import com.curiousapps.mvvm_practice.repositories.SchoolRepository;
import com.curiousapps.mvvm_practice.requests.TestClient;
import com.curiousapps.mvvm_practice.viewModels.DetailViewModel;

import java.util.List;

public class DetailActivity extends BaseActivity {

    private static final String TAG = "DetailActivity";
    private TextView detailSchoolName;
    private TextView detailSchoolAddress;
    private TextView detailSchoolCity;
    private TextView detailSchoolPhone;
    private TextView detailSchoolOverView;

    private ImageView detailSchoolWeb;
    private ImageView detailSchoolMail;

    private Button satButton;
    private ScrollView mScrollView;

    private DetailViewModel mDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        showProgressBar(true);

        mDetailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        initViews();
        getIncomingIntent();
        subscribeObserver();
    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("schoolList")){
            SchoolList schoolList = getIntent().getParcelableExtra("schoolList");
            Log.d(TAG, "getIncomingIntent: " + schoolList.getDbn());
            Log.d(TAG, "getIncomingIntent: " + schoolList.getOverview_paragraph());
            mDetailViewModel.searchSingleSchoolApi(schoolList.getDbn());
        }
    }

    private void subscribeObserver(){
        mDetailViewModel.getSchool().observe(this, new Observer<List<SchoolList>>() {
            @Override
            public void onChanged(List<SchoolList> schoolLists) {
                if (schoolLists != null){
                    for (SchoolList schoolList: schoolLists){
                        if (schoolList.getDbn().equals(mDetailViewModel.getDbn())){
                            Log.d(TAG, "Detail onChanged: ---><----------------------><---");
                            Log.d(TAG, "onChanged: " + schoolList.getSchool_name());
                            Log.d(TAG, "onChanged: " + schoolList.getOverview_paragraph());
                            setSchoolProperties(schoolLists);
                        }
                    }
                }
            }
        });
    }

    private void initViews(){
        detailSchoolName = findViewById(R.id.detail_school_name);
        detailSchoolAddress = findViewById(R.id.detail_school_address);
        detailSchoolCity = findViewById(R.id.detail_school_city);
        detailSchoolPhone = findViewById(R.id.detail_school_phone);
        detailSchoolOverView = findViewById(R.id.detail_school_overview);
        detailSchoolWeb = findViewById(R.id.detail_school_web);
        detailSchoolMail = findViewById(R.id.detail_school_mail);
        satButton = findViewById(R.id.sat_button);
        mScrollView = findViewById(R.id.parent);

    }

    private void setSchoolProperties(List<SchoolList> schoolLists){
        if (schoolLists !=null){
            for (SchoolList schoolList: schoolLists){
                detailSchoolName.setText(schoolList.getSchool_name());
                detailSchoolAddress.setText(schoolList.getPrimary_address_line_1());
                detailSchoolCity.setText(schoolList.getCity());
                detailSchoolPhone.setText(schoolList.getPhone_number());
                detailSchoolOverView.setText(schoolList.getOverview_paragraph());
            }
        }
        showParent();
        showProgressBar(false);
    }

    private void showParent(){
        mScrollView.setVisibility(View.VISIBLE);
    }

    private void nothing(){
        TestClient.getInstance().checkSingleItemFromListRetrofit();
    }
}
