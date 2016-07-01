package com.example.aishwarya.phonebook;

/**
 * Created by Aishwarya on 01/07/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{

    final static String DATABASE_NAME="phonebook";
    final static int Database_version=1;
    final static String TABLE_CONTACTS="contacts";
    final static String CONTACTS_ID="ID";
    final  static String KEY_IMAGE = "IMAGE";
    final static String CONTACTS_NAME="NAME";
    final static String CONTACTS_PHONE="PHONE";
    final static String QUERY_CREATE_CONTACT="CREATE TABLE IF NOT EXISTS "+ TABLE_CONTACTS+" ( "+ CONTACTS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_IMAGE+" BLOB, "+ CONTACTS_NAME+" TEXT ," + CONTACTS_PHONE+" TEXT)";
    final static String QUERY_DROP_CONTACT="DROP TABLE IF EXISTS "+TABLE_CONTACTS;

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

    public long insertContact(ContactHolder values){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues val=new ContentValues();
        byte[] image=DbBitmapUtility.getBytes(values.dp);
        val.put(KEY_IMAGE,image);
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
            byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE));
            Bitmap dp = DbBitmapUtility.getImage(image);
            String name=cursor.getString(cursor.getColumnIndex(CONTACTS_NAME));
            String phone=cursor.getString(cursor.getColumnIndex(CONTACTS_PHONE));
            ContactHolder contactHolder=new ContactHolder(id,dp,name,phone);
            contactHolderArrayList.add(contactHolder);
            cursor.moveToNext();
        }
        cursor.close();
        return contactHolderArrayList;
    }
}
class DbBitmapUtility {

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}

