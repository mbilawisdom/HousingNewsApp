package com.fesadeb.housingnews.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fesadeb.housingnews.R;
import com.fesadeb.housingnews.model.AttendModel;
import com.fesadeb.housingnews.model.ExhibitModel;
import com.fesadeb.housingnews.model.ForumModel;
import com.fesadeb.housingnews.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.valdesekamdem.library.mdtoast.MDToast;

public class Register extends AppCompatActivity {


    Button select_attend, select_exhibit, select_forum, select_conference;
    Button btn_register;
    EditText et_firstname, et_lastname, et_email,et_phone, et_states,
            et_organizaion ;

    EditText et_exhibit_person, et_exhibit_business;
    EditText et_forum_person, et_forum_position;

    ProgressBar bar;
    ProgressDialog progressDialog;

    String firstname, lastname, email, phone, states, organization;
    String exhibitPerson, exhibitBusiness, exhibitBook;
    String forumPerson, forumPosition;
    String country;
    SearchableSpinner select_book;
    CountryCodePicker select_country;
    TextView tv_forum, tv_conference;
    LinearLayout layout_register, layout_attend, layout_exhibit, layout_forum, layout_conference,
            layout_select;
    Boolean attendIsVisible, exhibitIsVisible, forumIsVisible, conferenceIsVisible,
            layoutIsVisible;
    View view;
    Utils utils;
//    LinearLayout mainDash;
//    private KProgressHUD hud;
    String uId;

    private FirebaseAuth mAuth;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        bar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
        select();
        initlayout();
        initAttend();
        initConference();
        initForum();
        initExhibit();
        initBtn();


        usersRef= FirebaseDatabase.getInstance().getReference().child("Users");
    }

    private void initlayout(){

        layout_register = findViewById(R.id.register);
        layout_attend = findViewById(R.id.attend);
        layout_exhibit = findViewById(R.id.exhibit);
        layout_forum = findViewById(R.id.ceo_forum);
        layout_conference = findViewById(R.id.conference);
        layout_select = findViewById(R.id.btn_select);
    }

    private void initAttend(){
        et_firstname = findViewById(R.id.et_firstname);
        et_lastname = findViewById(R.id.et_lastname);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone_number);
//        select_country = view.findViewById(R.id.countryCode);
        et_states = findViewById(R.id.states);
        et_organizaion = findViewById(R.id.organization);
        select_country = findViewById(R.id.countryCode);

    }

    private void initExhibit(){

        et_exhibit_person = findViewById(R.id.et_contactPerson);
        et_exhibit_business = findViewById(R.id.et_nature_business);
        select_book = findViewById(R.id.select_book);
    }

    private void initForum(){

        et_forum_person = findViewById(R.id.et_forum_contactPerson);
        et_forum_position = findViewById(R.id.et_forum_position);
        tv_forum = findViewById(R.id.tv_forum);
    }

    private void initConference(){
        tv_conference = findViewById(R.id.tv_conference);
    }


    private void select(){
        select_attend = findViewById(R.id.btn_attend);
        select_exhibit = findViewById(R.id.btn_exhibit);
        select_forum =  findViewById(R.id.btn_ceo_forum);
        select_conference = findViewById(R.id.btn_conference);

        select_attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_select.setVisibility(View.GONE);
                layoutIsVisible = false;
                attendIsVisible = true;
                exhibitIsVisible = false;
                forumIsVisible = false;
                conferenceIsVisible = false;
                layout_attend.setVisibility(View.VISIBLE);
                layout_register.setVisibility(View.VISIBLE);

            }
        });

        select_exhibit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_select.setVisibility(View.GONE);
                layoutIsVisible = false;
                attendIsVisible = true;
                exhibitIsVisible = true;
                forumIsVisible = false;
                conferenceIsVisible = false;
                layout_attend.setVisibility(View.VISIBLE);
                layout_exhibit.setVisibility(View.VISIBLE);
                layout_register.setVisibility(View.VISIBLE);
            }
        });

        select_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_select.setVisibility(View.GONE);
                layoutIsVisible = false;
                attendIsVisible = true;
                exhibitIsVisible = false;
                forumIsVisible = true;
                conferenceIsVisible = false;
                layout_attend.setVisibility(View.VISIBLE);
                layout_forum.setVisibility(View.VISIBLE);
                layout_register.setVisibility(View.VISIBLE);
            }
        });

        select_conference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_select.setVisibility(View.GONE);
                layoutIsVisible = false;
                attendIsVisible = false;
                exhibitIsVisible = false;
                forumIsVisible = false;
                conferenceIsVisible = true;
                layout_attend.setVisibility(View.VISIBLE);
                layout_conference.setVisibility(View.VISIBLE);
                layout_register.setVisibility(View.VISIBLE);
            }
        });

    }

    private void initBtn(){
        btn_register = findViewById(R.id.btn_sign);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isNetworkAvailable()) {

                    LayoutInflater inflater = LayoutInflater.from(Register.this);
                    View subView = inflater.inflate(R.layout.layout_privacy, null);
//        final EditText etHours = subView.findViewById(R.id.etHours);

                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setTitle("ACCEPT OUR PRIVACY POLICY");
                    builder.setView(subView);
                    AlertDialog alertDialog = builder.create();

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (attendIsVisible = true) {

                                firstname = et_firstname.getText().toString().trim();
                                lastname = et_lastname.getText().toString().trim();
                                email = et_email.getText().toString().trim();
                                phone = et_phone.getText().toString().trim();
                                states = et_states.getText().toString().trim();
                                organization = et_organizaion.getText().toString().trim();
                                country = select_country.getSelectedCountryName();


                                if (TextUtils.isEmpty(firstname)) {

                                    et_firstname.setError("Enter Your Firstname");

                                    return;
                                }
                                if (TextUtils.isEmpty(lastname)) {
                                    et_lastname.setError("Enter Your Lastname");

                                    return;
                                }
                                if (TextUtils.isEmpty(email)) {

                                    et_email.setError("Enter Correct Email");
                                    return;
                                }

                                if (TextUtils.isEmpty(phone)) {

                                    et_email.setError("Enter Correct Phone Number");
                                    return;
                                }

                                if (TextUtils.isEmpty(states)) {

                                    et_states.setError("Enter Your State Origin");
                                    return;
                                }
                                if (TextUtils.isEmpty(organization)) {

                                    et_organizaion.setError("Enter Your Company/organization");
                                } else {


                                    progressDialog.setMessage("Registering " + " " + lastname + " " + firstname);
                                    progressDialog.show();

                                    mAuth.createUserWithEmailAndPassword(email, phone)
                                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    progressDialog.dismiss();
                                                    if (!task.isSuccessful()) {
                                                        MDToast.makeText(Register.this, "Registration not successful",
                                                                MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
                                                    } else {
                                                        MDToast.makeText(Register.this, "Registered successful",
                                                                MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS).show();
                                                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                        uId = user.getUid();

                                                        AttendModel signup = new AttendModel(firstname, lastname, email, phone,
                                                                states, organization, country);
                                                        usersRef.child(uId).setValue(signup);
                                                        Intent signin = new Intent(Register.this, Dashboard.class);
                                                        signin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        signin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                        signin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        startActivity(signin);

                                                    }

                                                }
                                            });
                                }
                            } else if (exhibitIsVisible = true) {

                                firstname = et_firstname.getText().toString().trim();
                                lastname = et_lastname.getText().toString().trim();
                                email = et_email.getText().toString().trim();
                                phone = et_phone.getText().toString().trim();
                                states = et_states.getText().toString().trim();
                                organization = et_organizaion.getText().toString().trim();
                                exhibitPerson = et_exhibit_person.getText().toString().trim();
                                exhibitBusiness = et_exhibit_business.getText().toString().trim();
                                exhibitBook = select_book.getSelectedItem().toString();

                                if (TextUtils.isEmpty(firstname)) {

                                    et_firstname.setError("Enter Your Firstname");

                                    return;
                                }
                                if (TextUtils.isEmpty(lastname)) {
                                    et_lastname.setError("Enter Your Lastname");

                                    return;
                                }
                                if (TextUtils.isEmpty(email)) {

                                    et_email.setError("Enter Correct Email");
                                    return;
                                }
                                if (TextUtils.isEmpty(states)) {

                                    et_states.setError("Enter Your State Origin");
                                    return;
                                }
                                if (TextUtils.isEmpty(organization)) {

                                    et_organizaion.setError("Enter Your Company/organization");

                                    return;
                                }

                                if (TextUtils.isEmpty(exhibitPerson)) {

                                    et_exhibit_person.setError("Enter Contact Person");

                                    return;
                                }

                                if (TextUtils.isEmpty(exhibitBusiness)) {

                                    et_exhibit_business.setError("Enter Type Business");
                                    return;
                                }
                                if (exhibitBook.equalsIgnoreCase("Select Square Space")) {

                                    MDToast.makeText(Register.this, "Select A Booking Space",
                                            MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS).show();
                                } else {

                                    progressDialog.setMessage("Registering " + " " + lastname + " " + firstname);
                                    progressDialog.show();

                                    mAuth.createUserWithEmailAndPassword(email, phone)
                                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    progressDialog.dismiss();
                                                    if (!task.isSuccessful()) {
                                                        MDToast.makeText(Register.this, "Registration not successful",
                                                                MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
                                                    } else {
                                                        MDToast.makeText(Register.this, "Registered successful",
                                                                MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS).show();
                                                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                        uId = user.getUid();

                                                        ExhibitModel signup = new ExhibitModel(firstname, lastname, email, phone,
                                                                states, organization, country, exhibitPerson, exhibitBusiness, exhibitBook);
                                                        usersRef.child(uId).setValue(signup);
                                                        Intent signin = new Intent(Register.this, Dashboard.class);
                                                        signin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        signin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                        signin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        startActivity(signin);

                                                    }

                                                }
                                            });

                                }

                            } else if (forumIsVisible = true) {

                                firstname = et_firstname.getText().toString().trim();
                                lastname = et_lastname.getText().toString().trim();
                                email = et_email.getText().toString().trim();
                                phone = et_phone.getText().toString().trim();
                                states = et_states.getText().toString().trim();
                                organization = et_organizaion.getText().toString().trim();
                                forumPerson = et_forum_person.getText().toString().trim();
                                forumPosition = et_forum_position.getText().toString().trim();

                                if (TextUtils.isEmpty(firstname)) {

                                    et_firstname.setError("Enter Your Firstname");

                                    return;
                                }
                                if (TextUtils.isEmpty(lastname)) {
                                    et_lastname.setError("Enter Your Lastname");

                                    return;
                                }
                                if (TextUtils.isEmpty(email)) {

                                    et_email.setError("Enter Correct Email");
                                    return;
                                }
                                if (TextUtils.isEmpty(states)) {

                                    et_states.setError("Enter Your State Origin");
                                    return;
                                }
                                if (TextUtils.isEmpty(organization)) {

                                    et_organizaion.setError("Enter Your Company/organization");

                                    return;
                                }

                                if (TextUtils.isEmpty(forumPerson)) {

                                    et_forum_person.setError("Enter Contact Person");

                                    return;
                                }

                                if (TextUtils.isEmpty(forumPosition)) {

                                    et_forum_position.setError("Enter Office Position");
                                } else {

                                    progressDialog.setMessage("Registering " + " " + lastname + " " + firstname);
                                    progressDialog.show();

                                    mAuth.createUserWithEmailAndPassword(email, phone)
                                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    progressDialog.dismiss();
                                                    if (!task.isSuccessful()) {
                                                        MDToast.makeText(Register.this, "Registration not successful",
                                                                MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
                                                    } else {
                                                        MDToast.makeText(Register.this, "Registered successful",
                                                                MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS).show();
                                                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                        uId = user.getUid();

                                                        ForumModel signup = new ForumModel(firstname, lastname, email, phone,
                                                                states, organization, country, forumPerson, forumPosition);
                                                        usersRef.child(uId).setValue(signup);
                                                        Intent signin = new Intent(Register.this, Dashboard.class);
                                                        signin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        signin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                        signin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        startActivity(signin);

                                                    }

                                                }
                                            });
                                }


                            } else if (conferenceIsVisible = true) {


                                firstname = et_firstname.getText().toString().trim();
                                lastname = et_lastname.getText().toString().trim();
                                email = et_email.getText().toString().trim();
                                phone = et_phone.getText().toString().trim();
                                states = et_states.getText().toString().trim();
                                organization = et_organizaion.getText().toString().trim();


                                if (TextUtils.isEmpty(firstname)) {

                                    et_firstname.setError("Enter Your Firstname");

                                    return;
                                }
                                if (TextUtils.isEmpty(lastname)) {
                                    et_lastname.setError("Enter Your Lastname");

                                    return;
                                }
                                if (utils.isValidEmailId(email)) {

                                    et_email.setError("Enter Correct Email");
                                    return;
                                }
                                if (TextUtils.isEmpty(states)) {

                                    et_states.setError("Enter Your State Origin");
                                    return;
                                }
                                if (TextUtils.isEmpty(organization)) {

                                    et_organizaion.setError("Enter Your Company/organization");
                                } else {

                                    progressDialog.setMessage("Registering " + " " + lastname + " " + firstname);
                                    progressDialog.show();

                                    mAuth.createUserWithEmailAndPassword(email, phone)
                                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    progressDialog.dismiss();
                                                    if (!task.isSuccessful()) {
                                                        MDToast.makeText(Register.this, "Registration not successful",
                                                                MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
                                                    } else {
                                                        MDToast.makeText(Register.this, "Registered successful",
                                                                MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS).show();
                                                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                        uId = user.getUid();

                                                        AttendModel signup = new AttendModel(firstname, lastname, email, phone,
                                                                states, organization, country);
                                                        usersRef.child(uId).setValue(signup);
                                                        Intent signin = new Intent(Register.this, Dashboard.class);
                                                        signin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        signin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                        signin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        startActivity(signin);

                                                    }

                                                }
                                            });
                                }
                            }
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MDToast.makeText(Register.this, "Registration details not send pls Click Okay", Toast.LENGTH_SHORT);
                        }
                    });

                    builder.show();


                }else {
                    MDToast.makeText(Register.this, "On Data Connection",
                            MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();

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

    private  void openHourDialog() {

        LayoutInflater inflater = LayoutInflater.from(Register.this);
        View subView = inflater.inflate(R.layout.layout_privacy, null);
//        final EditText etHours = subView.findViewById(R.id.etHours);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Accept Our Privacy Policy");
        builder.setView(subView);
        AlertDialog alertDialog = builder.create();

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MDToast.makeText(Register.this, "Cancelled", Toast.LENGTH_SHORT);
            }
        });

        builder.show();


    }
}
