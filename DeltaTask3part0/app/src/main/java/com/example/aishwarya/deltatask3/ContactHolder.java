package com.example.aishwarya.deltatask3;

/**
 * Created by Aishwarya on 28/06/2016.
 */
public class ContactHolder {
    //class for saving a contact
    int id;
    String name;
    String phone;

    public ContactHolder(int id,String name,String phone) {
        this.id=id;
        this.name = name;
        this.phone=phone;
    }

    public ContactHolder(String name,String phone) {
        this.name = name;
        this.phone=phone;
    }
}
