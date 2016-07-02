package com.example.aishwarya.deltatask3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditContact extends AppCompatActivity {
    //activity for editing phone number
    EditText ename,ephone;
    Button esave;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        String n= getIntent().getStringExtra("Name");
        String p= getIntent().getStringExtra("Phone");
        ename=(EditText)findViewById(R.id.ename);
        ephone=(EditText)findViewById(R.id.ephone);
        ename.setText(n);
        ephone.setText(p);
        esave=(Button) findViewById(R.id.esave);
        dbHelper=new DBHelper(getApplicationContext());
        esave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editContact();
            }
        });
    }

    public void editContact(){
        String name=ename.getText().toString().trim();
        String phone=ephone.getText().toString().trim();

        ContactHolder values=new ContactHolder(name,phone);
        if(dbHelper.updateContact(values)!=-1){
            setResult(RESULT_OK);
            finish();
        }
    }

}
