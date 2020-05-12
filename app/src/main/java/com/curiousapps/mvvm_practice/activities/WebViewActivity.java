package com.curiousapps.mvvm_practice.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.curiousapps.mvvm_practice.R;

public class WebViewActivity extends AppCompatActivity {
    private static final String TAG = "WebViewActivity";
    private WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initWebView();
        getIncomingIntent();
    }

    private void getIncomingIntent() {
        myWebView = findViewById(R.id.web_view_id);
        String webUrl = getIntent().getExtras().getString("link");

        if (getIntent().hasExtra("link")) {
            if (!webUrl.contains("http://")){
                myWebView.loadUrl("https://" + webUrl);
            }else {
                myWebView.loadUrl(webUrl);
            }
        }else {
            Toast.makeText(this,
                    "URL is unavailable for this School", Toast.LENGTH_LONG).show();
        }
    }

    private void initWebView(){
        myWebView = new WebView(this);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()){
            myWebView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
