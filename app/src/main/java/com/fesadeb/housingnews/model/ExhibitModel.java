package com.fesadeb.housingnews.model;

public class ExhibitModel {

    String firstname, lastname , email,  phone,  states,  organization, country, person, business, book;

    public ExhibitModel( ) {
    }

    public ExhibitModel(String firstname, String lastname, String email, String phone, String states, String organization,
                        String country, String person, String business, String book) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.states = states;
        this.organization =  organization;
        this.country = country;
        this.person = person;
        this.business = business;
        this.book = book;


    }
}
