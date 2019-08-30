package com.fesadeb.housingnews.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fesadeb.housingnews.R;

public class PrivacyPolicy extends AppCompatActivity {
Button privacy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        privacy = findViewById(R.id.btn_privacy);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrivacyPolicy.this, PrivacyWeb.class));
            }
        });
    }
}
