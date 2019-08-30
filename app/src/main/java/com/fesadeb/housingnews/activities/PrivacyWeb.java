package com.fesadeb.housingnews.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.fesadeb.housingnews.R;
import com.fesadeb.housingnews.utils.MyWebViewClient;
import com.valdesekamdem.library.mdtoast.MDToast;

public class PrivacyWeb extends AppCompatActivity {
    WebView yt;
    ProgressBar pb;
    String url = "https://housingnews.org.ng/privacy-policy/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_web);


        yt=(WebView)findViewById(R.id.web);
        pb=(ProgressBar)findViewById(R.id.bar);
        if(isNetworkAvailable()){
            yt.loadUrl(url);
        }
        else{
            pb.setVisibility(View.GONE);
//            Snackbar snackbar = Snackbar
//                    .make(toolbar, "No Internet Connection..!", Snackbar.LENGTH_LONG)
//                    .setAction("RETRY", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Intent i = new Intent(PrivacyWeb.this, AIHSActivity.class);
//                            finish();
//                            startActivity(i);
//                        }
//                    });
//            snackbar.setActionTextColor(Color.CYAN);
//            snackbar.show();
            MDToast.makeText(PrivacyWeb.this, "No Internet Connection!",
                    MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
        }
        WebSettings webSettings = yt.getSettings();
        webSettings.setJavaScriptEnabled(true);
        yt.setWebViewClient(new MyWebViewClient());


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
