package com.example.nayeem.zippi;

import android.graphics.Bitmap;

/**
 * Created by DIVYA on 1/27/2016.
 */
public class EventPlannerModel {

    public Bitmap bitmap;
    public String organisationname;
    public String name;
    public String email;
    public String pwd;
    public String phone_number;
    public String address;
    public String events;
    public String otp;
    public String rating;
    public String rowId;
    public EventPlannerModel(){}



    public EventPlannerModel(String organisationname, Bitmap bitmap,String row)
    {   this.rowId=row;
        this.organisationname=organisationname;
        this.bitmap=bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getOrganisationname() {
        return organisationname;
    }

    public void setOrganisationname(String organisationname) {
        this.organisationname = organisationname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}

