package com.curiousapps.mvvm_practice.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.curiousapps.mvvm_practice.R;
import com.curiousapps.mvvm_practice.models.SchoolList;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    private TextView detailSchoolName;
    private TextView detailSchoolAddress;
    private TextView detailSchoolCity;
    private TextView detailSchoolPhone;
    private TextView detailSchoolOverView;

    private ImageView detailSchoolWeb;
    private ImageView detailSchoolMail;

    private Button satButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViews();
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("schoolList")){
            SchoolList schoolList = getIntent().getParcelableExtra("schoolList");
            Log.d(TAG, "getIncomingIntent: " + schoolList.getSchool_name());
        }
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

    }
}
