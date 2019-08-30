package com.fesadeb.housingnews.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.fesadeb.housingnews.R;
import com.fesadeb.housingnews.utils.MyWebViewClient;
import com.fesadeb.housingnews.utils.Utils;

public class AboutUs extends AppCompatActivity {

    WebView yt;
    ProgressBar pb,hb;
    String url = "https://abujainternationalhousingshow.com/about-2019-aihs1";
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = findViewById(R.id.toolba);
        setSupportActionBar(toolbar);



        yt=(WebView)findViewById(R.id.YTWeb);
        pb=(ProgressBar)findViewById(R.id.progressBa);
        hb=(ProgressBar)findViewById(R.id.horizontalP);
        if(isNetworkAvailable()){
            yt.loadUrl(url);
        }
        else{
            pb.setVisibility(View.GONE);
            Snackbar snackbar = Snackbar
                    .make(toolbar, "No Internet Connection..!", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(AboutUs.this, AboutUs.class);
                            finish();
                            startActivity(i);
                        }
                    });
            snackbar.setActionTextColor(Color.GRAY);
            snackbar.show();
        }
        WebSettings webSettings = yt.getSettings();
        webSettings.setJavaScriptEnabled(true);
        yt.setWebViewClient(new MyWebViewClient());

        yt.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                hb.setProgress(newProgress);
                if(newProgress==100){
                    pb.setVisibility(View.GONE);
                    hb.setVisibility(View.GONE);
                }
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
