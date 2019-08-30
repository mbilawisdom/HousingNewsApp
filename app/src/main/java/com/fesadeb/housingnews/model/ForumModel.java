package com.fesadeb.housingnews.model;

public class ForumModel {

    String firstname, lastname , email,  phone,  states,  organization, country, person, position;

    public ForumModel( ) {
    }

    public ForumModel(String firstname, String lastname, String email, String phone, String states, String organization,
                        String country, String person, String position) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.states = states;
        this.organization =  organization;
        this.country = country;
        this.person = person;
        this.position = position;


    }
}
