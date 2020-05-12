package com.curiousapps.mvvm_practice.activities;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.curiousapps.mvvm_practice.R;
import com.curiousapps.mvvm_practice.models.SchoolList;
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
    private String schoolDbn;

    private ImageView detailSchoolWeb;
    private ImageView detailSchoolMail;

    private Button satButton;
    private ScrollView mScrollView;

    private DetailViewModel mDetailViewModel;
    private SchoolList mSchoolList;

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

    private void getIncomingIntent() {
        if (getIntent().hasExtra("schoolList")) {
            mSchoolList = getIntent().getParcelableExtra("schoolList");
            Log.d(TAG, "getIncomingIntent: " + mSchoolList.getDbn());
            Log.d(TAG, "getIncomingIntent: " + mSchoolList.getOverview_paragraph());
            mDetailViewModel.searchSingleSchoolApi(mSchoolList.getDbn());
        }
    }

    private void subscribeObserver() {
        mDetailViewModel.getSchool().observe(this, new Observer<List<SchoolList>>() {
            @Override
            public void onChanged(List<SchoolList> schoolLists) {
                if (schoolLists != null) {
                    for (SchoolList schoolList : schoolLists) {
                        if (schoolList.getDbn().equals(mDetailViewModel.getDbn())) {
                            Log.d(TAG, "Detail onChanged: ---><----------------------><---");
                            Log.d(TAG, "onChanged: " + schoolList.getSchool_name());
                            Log.d(TAG, "onChanged: " + schoolList.getOverview_paragraph());
                            setSchoolProperties(schoolLists);
                            mDetailViewModel.setDidRetrieveSchool(true);
                        }
                    }
                }
            }
        });
        mDetailViewModel.isSchoolRequestTimedOut().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean && !mDetailViewModel.didRetrieveSchool()) {
                    Log.d(TAG, "<<onChanged>>: timed out");
                    displayErrorMessage("Error retrieving data." + "\nCheck Network Connection.");
                }
            }
        });
    }

    private void initViews() {
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

    private void setSchoolProperties(List<SchoolList> schoolLists) {
        if (schoolLists != null) {
            for (SchoolList schoolList : schoolLists) {
                detailSchoolName.setText(schoolList.getSchool_name());
                detailSchoolAddress.setText(schoolList.getPrimary_address_line_1());
                detailSchoolCity.setText(schoolList.getCity());
                detailSchoolPhone.setText(schoolList.getPhone_number());
                detailSchoolOverView.setText(schoolList.getOverview_paragraph());
            }
        }
        clickForSchoolPhone();
        clickForSchoolMail();
        clickForWebView();
        clickForSatActivity();

        showParent();
        showProgressBar(false);
    }

    private void showParent() {
        mScrollView.setVisibility(View.VISIBLE);
    }

    private void displayErrorMessage(String errorMessage){
        detailSchoolName.setText("ERROR!");
        detailSchoolAddress.setText("");
        detailSchoolCity.setText("Could not retrieve selected school...");
        detailSchoolPhone.setText("");
        satButton.setText("");
        detailSchoolOverView.setText(errorMessage);
        showParent();
        showProgressBar(false);
    }

    private void clickForSchoolPhone(){
        detailSchoolPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Phone Button", "Clicked: " + mSchoolList.getPhone_number().toString());

                String uri = "tel:" + mSchoolList.getPhone_number();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });
    }

    private void clickForSchoolMail(){

        detailSchoolMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Mail Button", "Clicked: " + mSchoolList.getSchool_email().toString());
                Intent mailIntent = new Intent(Intent.ACTION_SEND);
                mailIntent.setType("message/rfc822");
                mailIntent.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{mSchoolList.getSchool_email()});
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, "School Information");
                mailIntent.putExtra(Intent.EXTRA_TEXT, "");
                try {
                 startActivity(Intent.createChooser(mailIntent, "Send mail..."));
                }catch (ActivityNotFoundException ex){
                    Toast.makeText(DetailActivity.this,
                            "There are no email installed clients on this phone.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void clickForWebView(){
        detailSchoolWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Link Button", "Clicked: " + mSchoolList.getWebsite().toString());

                //Add intent to open web page
                Intent intent = new Intent(DetailActivity.this, WebViewActivity.class);
                intent.putExtra("link", mSchoolList.getWebsite());
                startActivity(intent);
            }
        });
    }

    private void clickForSatActivity(){
        satButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent satIntent = new Intent(DetailActivity.this, SatActivity.class);
                Log.d(TAG, "SchoolExtraSend dbn: " + mSchoolList.getDbn());
                satIntent.putExtra("schoolDbn", mSchoolList);

                //mSchoolRecyclerViewAdapter.getSelectedSchool(position)

                DetailActivity.this.startActivity(satIntent);
            }
        });
    }

    private void nothing() {
        TestClient.getInstance().checkSingleItemFromListRetrofit();
    }
}
