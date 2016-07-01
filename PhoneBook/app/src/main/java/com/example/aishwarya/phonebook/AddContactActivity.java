package com.example.aishwarya.phonebook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.IOException;

public class AddContactActivity extends AppCompatActivity {

    EditText etName,etPhone;
    DBHelper dbHelper;
    ImageView dp;
    Button btn;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        etName=(EditText)findViewById(R.id.editText);
        etPhone=(EditText)findViewById(R.id.editText2);
        Button buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        dbHelper=new DBHelper(getApplicationContext());

        btn=(Button)findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            ImageView imageView = (ImageView) findViewById(R.id.imgView);

            Bitmap bmp = null;
            try {
                bmp = getBitmapFromUri(selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(bmp);

        }


    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    public void saveContact(){

        try {
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            dp = (ImageView) findViewById(R.id.imgView);
            Bitmap bm = ((BitmapDrawable) dp.getDrawable()).getBitmap();

            ContactHolder values = new ContactHolder(bm,name,phone);
            if (dbHelper.insertContact(values) != -1) {
                setResult(RESULT_OK);
                finish();
            }
        }catch(Exception e){

            Toast.makeText(AddContactActivity.this,"One of the fields is empty,please go back and try again",Toast.LENGTH_LONG).show();
        }
    }
}

