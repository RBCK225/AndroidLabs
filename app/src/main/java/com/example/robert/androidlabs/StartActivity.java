package com.example.robert.androidlabs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class StartActivity extends Activity {

    protected final String activityStart = "activityStart";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    protected void onResume()
    {
        super.onResume();
        Log.i(activityStart, "In onResume");
    }
    protected void onStart()
    {
        super.onStart();
        Log.i(activityStart, "In onStart");
    }

    protected void onPause()
    {
        super.onPause();
        Log.i(activityStart, "In onPause");
    }

    protected void onStop()
    {
        super.onStop();
        Log.i(activityStart, "In onStop");
    }

    protected void onDestroy()
    {
        super.onDestroy();
        Log.i(activityStart, "In onDestroy");
    }
}
