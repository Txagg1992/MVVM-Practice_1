package com.curiousapps.mvvm_practice.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.curiousapps.mvvm_practice.R;
import com.curiousapps.mvvm_practice.models.SchoolList;
import com.curiousapps.mvvm_practice.models.SchoolSAT;
import com.curiousapps.mvvm_practice.viewModels.SatViewModel;

import java.util.List;

public class SatActivity extends BaseActivity {

    private static final String TAG = "SatActivity";

    public static final String EXTRA = "schoolDbn";

    private TextView satSchoolName;
    private TextView satNumberOfTests;
    private TextView satReadingAverage;
    private TextView satWritingAverage;
    private TextView satMathAverage;
    private ConstraintLayout constraintLayout;

    private SchoolSAT mSchoolSat;
    private SchoolList mSchoolList;
    private SatViewModel mSatViewModel;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sat);

        mSatViewModel = new ViewModelProvider(this).get(SatViewModel.class);
        initViews();
        getIncomingIntent();
        subscribeObserver();
    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("schoolDbn")){
            mSchoolList = getIntent().getParcelableExtra("schoolDbn");
            Log.d(TAG, "getIncomingIntent for SAT : " + mSchoolList.getDbn());
            Log.d(TAG, "getIncomingIntent for SAT : " + mSchoolList.getSchool_name());
            mSatViewModel.searchSchoolSat(mSchoolList.getDbn());
        }
//        if (getIntent().hasExtra("schoolDbn")){
//            extras = getIntent().getExtras();
//            if (extras != null){
//                String dbnExtra = extras.getString("schoolDbn");
//                Log.d(TAG, "ExtrasValue: " + dbnExtra.toString());
//                mSatViewModel.searchSchoolSat(mSchoolSat.getDbn());
//            }else {
//                //TODO inflate view with message along with Toast in sat_school_name.
//                Toast.makeText(this,
//                        "This school has not reported any SAT scores for the given year", Toast.LENGTH_LONG).show();
//            }
//        }
    }

    private void subscribeObserver(){
        mSatViewModel.getSchoolSat().observe(this, new Observer<List<SchoolSAT>>() {
            @Override
            public void onChanged(List<SchoolSAT> schoolSATS) {
                if (schoolSATS != null){
                    for (SchoolSAT schoolSAT: schoolSATS){
                        Log.d(TAG, "SAT onChanged: _-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
                        Log.d(TAG, "onChanged: " + schoolSAT.getDbn());
                        Log.d(TAG, "onChanged: " + schoolSAT.getSchool_Name());
                    }
                }else {
                    //TODO inflate view with message along with Toast in sat_school_name.
                    Toast.makeText(SatActivity.this,
                            "This school has not reported any SAT scores for the given year", Toast.LENGTH_LONG ).show();
                }
            }
        });
    }

    private void initViews(){
        satSchoolName = findViewById(R.id.sat_school_name);
        satNumberOfTests = findViewById(R.id.sat_number_of_tests);
        satReadingAverage = findViewById(R.id.sat_reading_average);
        satWritingAverage = findViewById(R.id.sat_writing_average);
        satMathAverage = findViewById(R.id.sat_math_average);
        constraintLayout = findViewById(R.id.constraint_layout);
    }
}
