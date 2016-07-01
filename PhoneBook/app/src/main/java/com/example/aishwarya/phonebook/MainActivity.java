package com.example.aishwarya.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
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

    private void addToList() {
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
            if(layout==null){
                layout=getLayoutInflater().inflate(R.layout.item_contact_list,parent,false);

            }
            ImageView dp=(ImageView)layout.findViewById(R.id.imageView);
            TextView tvName= (TextView) layout.findViewById(R.id.textView2);
            TextView tvPhone= (TextView) layout.findViewById(R.id.textView3);
            ContactHolder contactHolder=contactHolderArrayList.get(position);
            dp.setImageBitmap(contactHolder.dp);
            tvName.setText(contactHolder.name);
            tvPhone.setText(contactHolder.phone);

            return layout;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100&&resultCode==RESULT_OK){
            addToList();
            Toast.makeText(MainActivity.this,"Contact added successfully",Toast.LENGTH_SHORT).show();
        }
    }

    public void save(){
        startActivityForResult(new Intent(MainActivity.this,AddContactActivity.class),100);
    }
}
