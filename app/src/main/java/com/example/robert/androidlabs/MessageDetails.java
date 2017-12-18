package com.example.robert.androidlabs;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;


public class MessageDetails extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

        if (savedInstanceState == null){
            String passedMessage = getIntent().getStringExtra("Message");
            String passedID = getIntent().getStringExtra("Key");
            Bundle info = new Bundle();
            info.putString("Message", passedMessage);
            info.putString("Key", passedID);
            MessageFragment messageFrag = new MessageFragment();
            messageFrag.setArguments(info);
            FragmentTransaction ft =  getFragmentManager().beginTransaction();
            ft.replace(R.id.emptyFragment, messageFrag);
            ft.commit();
        }

    }
}
