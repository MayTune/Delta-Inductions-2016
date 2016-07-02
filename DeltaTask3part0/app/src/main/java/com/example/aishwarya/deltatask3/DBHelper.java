package com.example.aishwarya.deltatask3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Aishwarya on 28/06/2016.
 */
public class DBHelper extends SQLiteOpenHelper{

    final static String DATABASE_NAME="phonebook";
    final static int Database_version=1;
    final static String TABLE_CONTACTS="contacts";
    final static String CONTACTS_ID="ID";
    final static String CONTACTS_NAME="NAME";
    final static String CONTACTS_PHONE="PHONE";
    final static String QUERY_CREATE_CONTACT="CREATE TABLE IF NOT EXISTS "+ TABLE_CONTACTS+" ( "+ CONTACTS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ CONTACTS_NAME+" TEXT ," + CONTACTS_PHONE+" TEXT)";
    final static String QUERY_DROP_CONTACT="DROP TABLE IF EXISTS "+TABLE_CONTACTS;
    SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(QUERY_CREATE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QUERY_DROP_CONTACT);
    }

    //for inserting new contact
    public long insertContact(ContactHolder values){
        db=getWritableDatabase();
        ContentValues val=new ContentValues();
        val.put(CONTACTS_NAME,values.name);
        val.put(CONTACTS_PHONE,values.phone);
        long ret=db.insert(TABLE_CONTACTS,null,val);
        return ret;

    }

    public ArrayList<ContactHolder> getContactList(){
        ArrayList<ContactHolder>contactHolderArrayList=new ArrayList<>();
       SQLiteDatabase db=getReadableDatabase();
       Cursor cursor= db.query(TABLE_CONTACTS,null,null,null,null,null,null);
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++){
            int id=cursor.getInt(cursor.getColumnIndex(CONTACTS_ID));
            String name=cursor.getString(cursor.getColumnIndex(CONTACTS_NAME));
            String phone=cursor.getString(cursor.getColumnIndex(CONTACTS_PHONE));
            ContactHolder contactHolder=new ContactHolder(id,name,phone);
            contactHolderArrayList.add(contactHolder);
            cursor.moveToNext();
        }
        return contactHolderArrayList;
    }

    //for updating phone number
    public long updateContact(ContactHolder values) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_NAME,values.name);
        contentValues.put(CONTACTS_PHONE,values.phone);
        return db.update(TABLE_CONTACTS,contentValues," "+ CONTACTS_NAME + " = ? ",new String[] { values.name});

    }


}
