package com.example.aishwarya.phonebook;

import android.graphics.Bitmap;

/**
 * Created by Aishwarya on 01/07/2016.
 */
public class ContactHolder {
    int id;
    String name;
    String phone;
    Bitmap dp;

    public ContactHolder(int id,Bitmap dp,String name,String phone) {
        this.id=id;
        this.name = name;
        this.phone=phone;
        this.dp=dp;
    }
    public ContactHolder(Bitmap dp,String name,String phone) {
        this.name = name;
        this.phone=phone;
        this.dp=dp;
    }

    public ContactHolder(String name,String phone) {
        this.name = name;
        this.phone=phone;
    }
}

