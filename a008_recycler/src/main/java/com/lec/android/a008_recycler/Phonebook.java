package com.lec.android.a008_recycler;

import java.io.Serializable;

public class Phonebook implements Serializable {

    int photo;
    String name;
    String email;
    String phone;

    public Phonebook(){}

    public Phonebook(int photo, String name, String email, String phone) {
        this.photo = photo;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
