package com.lec.android.a008_practice;

import java.io.Serializable;

public class Profile implements Serializable {

    String name;
    String age;
    String adress;

    public Profile(){}  //기본생성자

    public Profile(String name, String age, String adress) {
        this.name = name;
        this.age = age;
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
