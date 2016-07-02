package com.example.aishwarya.deltatask3;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{
    ListView lvContact;
    Button add;
    ArrayList<ContactHolder> contactHolderArrayList;
    DBHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContact=(ListView)findViewById(R.id.listView);
        //button for adding new contacts
        add=(Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        dbhelper=new DBHelper(getApplicationContext());
        addToList();


    }

    public void addToList() {
        contactHolderArrayList=dbhelper.getContactList();
        lvContact.setAdapter(new ContactAdapter());
    }

    public class ContactAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return contactHolderArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View layout=convertView;
            ContactHolder contactHolder=contactHolderArrayList.get(position);
            if(layout==null){
                layout=getLayoutInflater().inflate(R.layout.item_contact_list,parent,false);

            }
            final TextView tvName= (TextView) layout.findViewById(R.id.textView2);
            final TextView tvPhone= (TextView) layout.findViewById(R.id.textView3);
            //edit button for editing phone number
            Button edit=(Button)layout.findViewById(R.id.edit);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,EditContact.class);
                    intent.putExtra("Name",tvName.getText().toString());
                    intent.putExtra("Phone",tvPhone.getText().toString());
                    startActivityForResult(intent,200);
                }
            });
            tvName.setText(contactHolder.name);
            tvPhone.setText(contactHolder.phone);

            return layout;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //on adding new contacts
        if(requestCode==100&&resultCode==RESULT_OK){
            addToList();
            Toast.makeText(MainActivity.this,"Contact added successfully",Toast.LENGTH_SHORT).show();
        }

        //on editing a phone number
        else if(requestCode==200&&resultCode==RESULT_OK){
            Toast.makeText(MainActivity.this,"Contact edited successfully",Toast.LENGTH_SHORT).show();
            addToList();
        }
    }



    public void save(){
        startActivityForResult(new Intent(MainActivity.this,AddContactActivity.class),100);
    }



}


