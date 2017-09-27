package com.example.robert.androidlabs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ListItemsActivity extends Activity {

    protected final String listActivity = "listActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
    }

    protected void onResume()
    {
        super.onResume();
        Log.i(listActivity, "In onResume");
    }
    protected void onStart()
    {
        super.onStart();
        Log.i(listActivity, "In onStart");
    }

    protected void onPause()
    {
        super.onPause();
        Log.i(listActivity, "In onPause");
    }

    protected void onStop()
    {
        super.onStop();
        Log.i(listActivity, "In onStop");
    }

    protected void onDestroy()
    {
        super.onDestroy();
        Log.i(listActivity, "In onDestroy");
    }
}
