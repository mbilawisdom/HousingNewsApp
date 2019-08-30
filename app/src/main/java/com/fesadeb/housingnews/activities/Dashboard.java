package com.fesadeb.housingnews.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.fesadeb.housingnews.R;


public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CardView btnNews, btnShow, btnReg, btnLive;
    LinearLayout mainDash;
    FragmentTransaction transaction;

    String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.logo);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initViews();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

//            int count = getSupportFragmentManager().getBackStackEntryCount();
//
//            if (count == 0) {
//                super.onBackPressed();
//                //additional code
//            } else {
//                getSupportFragmentManager().popBackStack();
//            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(Dashboard.this, PrivacyPolicy.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_news) {
            // Handle the camera action

            startActivity(new Intent(Dashboard.this, News.class));
        } else if (id == R.id.nav_show) {

            startActivity(new Intent(Dashboard.this, AboutUs.class));

        } else if (id == R.id.nav_reg) {

            startActivity(new Intent(Dashboard.this, Register.class));

        } else if (id == R.id.nav_live) {
            startActivity(new Intent(Dashboard.this, AIHSActivity.class));
        } else if (id == R.id.nav_contact) {
            startActivity(new Intent(Dashboard.this, ContactUs.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(Dashboard.this, AboutUs.class));
        }
        else if(id == R.id.nav_privacy){
            startActivity(new Intent(Dashboard.this, PrivacyPolicy.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initViews(){
      findViewById(R.id.housingNews).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


            startActivity(new Intent(Dashboard.this, News.class));


           }
       });
        findViewById(R.id.abuja_housing_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Dashboard.this, AboutUs.class));
            }
        });

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Dashboard.this, Register.class));
            }
        });

        findViewById(R.id.liveStreams).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, AIHSActivity.class));
            }
        });
    }


}
