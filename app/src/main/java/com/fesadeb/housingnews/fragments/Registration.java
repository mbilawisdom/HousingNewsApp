//package com.fesadeb.housingnews.fragments;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v4.app.Fragment;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.example.housingnews.R;
//import com.fesadeb.housingnews.activities.Dashboard;
//import com.fesadeb.housingnews.activities.News;
//import com.fesadeb.housingnews.model.AttendModel;
//import com.fesadeb.housingnews.utils.Utils;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.hbb20.CCPCountry;
//import com.hbb20.CountryCodePicker;
//import com.kaopiz.kprogresshud.KProgressHUD;
//import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
//import com.valdesekamdem.library.mdtoast.MDToast;
//
//
//public class Registration extends Fragment {
//
//    Button select_attend, select_exhibit, select_forum, select_conference;
//    Button btn_register;
//    EditText et_firstname, et_lastname, et_email,et_phone, et_states,
//           et_organizaion ;
//
//    EditText et_exhibit_person, et_exhibit_business;
//    EditText et_forum_person, et_forum_position;
//
//    ProgressBar bar;
//    ProgressDialog progressDialog;
//
//    String firstname, lastname, email, phone, states, organization;
//    String exhibitPerson, exhibitBusiness;
//    String forumPerson, forumPosition;
//    String country;
//    SearchableSpinner  select_book;
//    CountryCodePicker select_country;
//    TextView tv_forum, tv_conference;
//    LinearLayout layout_register, layout_attend, layout_exhibit, layout_forum, layout_conference,
//            layout_select;
//     Boolean attendIsVisible, exhibitIsVisible, forumIsVisible, conferenceIsVisible,
//            layoutIsVisible;
//    View view;
//    Utils utils;
//    LinearLayout mainDash;
//    private KProgressHUD hud;
//    String uId;
//
//    private FirebaseAuth mAuth;
//    private DatabaseReference usersRef;
//Context context;
//    public Registration() {
//        // Required empty public constructor
//    }
//
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
////        mAuth = FirebaseAuth.getInstance();
////        regDetails= FirebaseDatabase.getInstance().getReference().child("Users");
//
//
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//      view = inflater.inflate(R.layout.fragment_registration, container, false);
//
//
////        progressDialog = new ProgressDialog(context);
//
//        progressDialog = new ProgressDialog(context);
//        bar = new ProgressBar(context, null, android.R.attr.progressBarStyleSmall);
//        select();
//       initlayout();
//       initAttend();
//       initConference();
//       initForum();
//       initExhibit();
//     initBtn();
//
//        mAuth = FirebaseAuth.getInstance();
//        usersRef= FirebaseDatabase.getInstance().getReference().child("Users");
//   return view;
//    }
//
////    private void initlayout(){
////
////        layout_register = view.findViewById(R.id.register);
////        layout_attend = view.findViewById(R.id.attend);
////        layout_exhibit = view.findViewById(R.id.exhibit);
////        layout_forum = view.findViewById(R.id.ceo_forum);
////        layout_conference = view.findViewById(R.id.conference);
////        layout_select = view.findViewById(R.id.btn_select);
////    }
////
////    private void initAttend(){
////        et_firstname = view.findViewById(R.id.et_firstname);
////        et_lastname = view.findViewById(R.id.et_lastname);
////        et_email = view.findViewById(R.id.et_email);
////        et_phone = view.findViewById(R.id.et_phone_number);
//////        select_country = view.findViewById(R.id.countryCode);
////        et_states = view.findViewById(R.id.states);
////        et_organizaion = view.findViewById(R.id.organization);
////        select_country = view.findViewById(R.id.countryCode);
////
////    }
////
////    private void initExhibit(){
////
////        et_exhibit_person = view.findViewById(R.id.et_contactPerson);
////        et_exhibit_business = view.findViewById(R.id.et_nature_business);
////        select_book = view.findViewById(R.id.select_book);
////    }
////
////    private void initForum(){
////
////        et_forum_person = view.findViewById(R.id.et_forum_contactPerson);
////        et_forum_position = view.findViewById(R.id.et_forum_position);
////        tv_forum = view.findViewById(R.id.tv_forum);
////    }
////
////    private void initConference(){
////        tv_conference = view.findViewById(R.id.tv_conference);
////    }
////
////
////    private void select(){
////        select_attend = view.findViewById(R.id.btn_attend);
////        select_exhibit = view.findViewById(R.id.btn_exhibit);
////        select_forum =  view.findViewById(R.id.btn_ceo_forum);
////        select_conference = view.findViewById(R.id.btn_conference);
////
////        select_attend.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                layout_select.setVisibility(View.GONE);
////                layoutIsVisible = false;
////                attendIsVisible = true;
////                exhibitIsVisible = false;
////                forumIsVisible = false;
////                conferenceIsVisible = false;
////                layout_attend.setVisibility(View.VISIBLE);
////                layout_register.setVisibility(View.VISIBLE);
////
////            }
////        });
////
////        select_exhibit.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                layout_select.setVisibility(View.GONE);
////                layoutIsVisible = false;
////                attendIsVisible = true;
////                exhibitIsVisible = true;
////                forumIsVisible = false;
////                conferenceIsVisible = false;
////                layout_attend.setVisibility(View.VISIBLE);
////                layout_exhibit.setVisibility(View.VISIBLE);
////                layout_register.setVisibility(View.VISIBLE);
////            }
////        });
////
////        select_forum.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                layout_select.setVisibility(View.GONE);
////                layoutIsVisible = false;
////                attendIsVisible = true;
////                exhibitIsVisible = false;
////                forumIsVisible = true;
////                conferenceIsVisible = false;
////                layout_attend.setVisibility(View.VISIBLE);
////                layout_forum.setVisibility(View.VISIBLE);
////                layout_register.setVisibility(View.VISIBLE);
////            }
////        });
////
////        select_conference.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                layout_select.setVisibility(View.GONE);
////                layoutIsVisible = false;
////                attendIsVisible = false;
////                exhibitIsVisible = false;
////                forumIsVisible = false;
////                conferenceIsVisible = true;
////                layout_attend.setVisibility(View.VISIBLE);
////                layout_conference.setVisibility(View.VISIBLE);
////                layout_register.setVisibility(View.VISIBLE);
////            }
////        });
////
////    }
////
////    private void initBtn(){
////        btn_register = view.findViewById(R.id.btn_signup);
////
////        btn_register.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (attendIsVisible = true){
////
////                    firstname = et_firstname.getText().toString().trim();
////                    lastname = et_lastname.getText().toString().trim();
////                    email = et_email.getText().toString().trim();
////                    phone = et_phone.getText().toString().trim();
////                    states =  et_states.getText().toString().trim();
////                    organization = et_organizaion.getText().toString().trim();
////                    country = select_country.getSelectedCountryName();
////
////
////
////                    if (TextUtils.isEmpty(firstname)){
////
////                        et_firstname.setError("Enter Your Firstname");
////
////                        return;
////                    }
////                    if (TextUtils.isEmpty(lastname)){
////                        et_lastname.setError("Enter Your Lastname");
////
////                        return;
////                    }
////                    if (utils.isValidEmailId(email)){
////
////                        et_email.setError("Enter Correct Email");
////                        return;
////                    }
////
////                    if (utils.isValidPhoneNumber(context, phone)){
////
////                        et_email.setError("Enter Correct Phone Number");
////                        return;
////                    }
////
////                    if (TextUtils.isEmpty(states)){
////
////                        et_states.setError("Enter Your State Origin");
////                        return;
////                    }
////                    if (TextUtils.isEmpty(organization)){
////
////                        et_organizaion.setError("Enter Your Company/organization");
////                    }
////
////                    else {
////
//////                        progressDialog.setMessage("Registering ... To Attend" + firstname + " " + lastname + " ");
//////                        progressDialog.show();
//////
//////                        mAuth.createUserWithEmailAndPassword(email, phone)
//////                            .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
//////                                @Override
//////                                public void onComplete(@NonNull Task<AuthResult> task) {
//////                                    progressDialog.dismiss();
//////                                    if (!task.isSuccessful()) {
//////                                        MDToast.makeText(context, "Sign up not successful",
//////                                                MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
//////                                    } else {
//////                                        MDToast.makeText(context, "Sign up successful",
//////                                                MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS).show();
//////                                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//////                                        uId = user.getUid();
//////
//////                                        AttendModel signup = new AttendModel(firstname, lastname, email, phone,
//////                                                states, organization, country);
//////                                        usersRef.child(uId).setValue(signup);
//////                                        Intent signin = new Intent(context, Dashboard.class);
//////                                        signin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//////                                        signin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//////                                        signin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//////                                        startActivity(signin);
//////                                    }
//////                                }
//////
//////                            }
////
////                        progressDialog.setMessage("Signing up user...");
////                        progressDialog.show();
////
////                        mAuth.createUserWithEmailAndPassword(email, phone)
////                                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
////                                    @Override
////                                    public void onComplete(@NonNull Task<AuthResult> task) {
////                                        progressDialog.dismiss();
////                                        if (!task.isSuccessful()){
////                                            MDToast.makeText(context,"Sign up not successful",
////                                                    MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
////                                        } else{
////                                            MDToast.makeText(context,"Sign up successful",
////                                                    MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS).show();
////                                            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////                                            uId = user.getUid();
////
////                                            AttendModel signup = new AttendModel(firstname, lastname, email, phone,
////                                            states, organization, country);
////                                            usersRef.child(uId).setValue(signup);
////                                            Intent signin = new Intent(getActivity(),Dashboard.class);
////                                            signin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                                            signin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                                            signin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                                            startActivity(signin);
////
////                                        }
////
////                                    }
////                                });
////                    }
////                }
////
////                else if (exhibitIsVisible = true){
////
////                    firstname = et_firstname.getText().toString().trim();
////                    lastname = et_lastname.getText().toString().trim();
////                    email = et_email.getText().toString().trim();
////                    phone = et_phone.getText().toString().trim();
////                    states =  et_states.getText().toString().trim();
////                    organization = et_organizaion.getText().toString().trim();
////                    exhibitPerson = et_exhibit_person.getText().toString().trim();
////                    exhibitBusiness = et_exhibit_business.getText().toString().trim();
////
////                    if (TextUtils.isEmpty(firstname)){
////
////                        et_firstname.setError("Enter Your Firstname");
////
////                        return;
////                    }
////                    if (TextUtils.isEmpty(lastname)){
////                        et_lastname.setError("Enter Your Lastname");
////
////                        return;
////                    }
////                    if (utils.isValidEmailId(email)){
////
////                        et_email.setError("Enter Correct Email");
////                        return;
////                    }
////                    if (TextUtils.isEmpty(states)){
////
////                        et_states.setError("Enter Your State Origin");
////                        return;
////                    }
////                    if (TextUtils.isEmpty(organization)){
////
////                        et_organizaion.setError("Enter Your Company/organization");
////
////                        return;
////                    }
////
////                    if (TextUtils.isEmpty(exhibitPerson)){
////
////                        et_exhibit_person.setError("Enter Contact Person");
////
////                        return;
////                    }
////
////                    if (TextUtils.isEmpty(exhibitBusiness)){
////
////                        et_exhibit_business.setError("Enter Type Business");
////                    }
////
////                    else {
////
////
////
////                    }
////
////                } else if (forumIsVisible = true){
////
////                    firstname = et_firstname.getText().toString().trim();
////                    lastname = et_lastname.getText().toString().trim();
////                    email = et_email.getText().toString().trim();
////                    phone = et_phone.getText().toString().trim();
////                    states =  et_states.getText().toString().trim();
////                    organization = et_organizaion.getText().toString().trim();
////                    forumPerson = et_forum_person.getText().toString().trim();
////                    forumPosition = et_forum_position.getText().toString().trim();
////
////                    if (TextUtils.isEmpty(firstname)){
////
////                        et_firstname.setError("Enter Your Firstname");
////
////                        return;
////                    }
////                    if (TextUtils.isEmpty(lastname)){
////                        et_lastname.setError("Enter Your Lastname");
////
////                        return;
////                    }
////                    if (utils.isValidEmailId(email)){
////
////                        et_email.setError("Enter Correct Email");
////                        return;
////                    }
////                    if (TextUtils.isEmpty(states)){
////
////                        et_states.setError("Enter Your State Origin");
////                        return;
////                    }
////                    if (TextUtils.isEmpty(organization)){
////
////                        et_organizaion.setError("Enter Your Company/organization");
////
////                        return;
////                    }
////
////                    if (TextUtils.isEmpty(forumPerson)){
////
////                        et_forum_person.setError("Enter Contact Person");
////
////                        return;
////                    }
////
////                    if (TextUtils.isEmpty(forumPosition)){
////
////                        et_forum_position.setError("Enter Office Position");
////                    }
////
////                    else {
////
////
////                    }
////
////
////                } else  if (conferenceIsVisible =  true){
////
////
////                    firstname = et_firstname.getText().toString().trim();
////                    lastname = et_lastname.getText().toString().trim();
////                    email = et_email.getText().toString().trim();
////                    phone = et_phone.getText().toString().trim();
////                    states =  et_states.getText().toString().trim();
////                    organization = et_organizaion.getText().toString().trim();
////
////
////                    if (TextUtils.isEmpty(firstname)){
////
////                        et_firstname.setError("Enter Your Firstname");
////
////                        return;
////                    }
////                    if (TextUtils.isEmpty(lastname)){
////                        et_lastname.setError("Enter Your Lastname");
////
////                        return;
////                    }
////                    if (utils.isValidEmailId(email)){
////
////                        et_email.setError("Enter Correct Email");
////                        return;
////                    }
////                    if (TextUtils.isEmpty(states)){
////
////                        et_states.setError("Enter Your State Origin");
////                        return;
////                    }
////                    if (TextUtils.isEmpty(organization)){
////
////                        et_organizaion.setError("Enter Your Company/organization");
////                    }
////
////                    else {
////
////
////                    }
////                }
////            }
////        });
////    }
//
//
//}
