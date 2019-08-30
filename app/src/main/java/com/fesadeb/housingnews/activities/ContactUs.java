package com.fesadeb.housingnews.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fesadeb.housingnews.R;

public class ContactUs extends AppCompatActivity {

    private ImageView call, mail, web, sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        call =  findViewById(R.id.call);
        mail =  findViewById(R.id.mail);
        web =  findViewById(R.id.web);
        sms =  findViewById(R.id.sms);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "+2348166570090";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });


        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_VIEW);

                String aEmailList[] = { "abujahousingshow@gmail.com"};
                startActivity(Intent.createChooser(emailIntent, "Send your email using:"));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, aEmailList);
//                emailIntent.setType("plain/text");


            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "+2348166570090";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
            }
        });


        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.abujainternationalhousingshow.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}
