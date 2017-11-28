package com.example.robert.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends Activity {

    protected final String activityStart = "activityStart";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button startActivityButton = (Button) findViewById(R.id.button);

        startActivityButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), ListItemsActivity.class);
                startActivityForResult(startIntent, 10);

            }
        });

        Button chatStart = findViewById(R.id.button4);

        chatStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.i("StartActivity", "User clicked Start Chat");
                Intent chatIntent = new Intent(getApplicationContext(), ChatWindow.class);
                startActivityForResult(chatIntent, 10);
            }

        });

        Button weatherForecast = (Button) findViewById(R.id.button5);

        weatherForecast.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent weatherIntent = new Intent(getApplicationContext(), WeatherForecast.class);
                startActivityForResult(weatherIntent, 10);

            }
        });
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        int numReceived = resultCode;
        if(requestCode == 10)
        {
            if (resultCode == Activity.RESULT_OK){
                String messagePassed = data.getStringExtra("Response");
                Toast.makeText(StartActivity.this, messagePassed,
                        Toast.LENGTH_LONG).show();
            }

            Log.i(activityStart, "Returned to StartActivity.onActivityResult");
        }
    }
}
