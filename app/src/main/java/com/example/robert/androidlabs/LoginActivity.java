package com.example.robert.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends Activity {

    protected final String email = "logInName";
    protected Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences userEmail = getSharedPreferences(email, 0);
        SharedPreferences.Editor editor = userEmail.edit();
        editor.putString("DefaultEmail", "email@domain.com");
        editor.commit();

        loginButton = (Button) findViewById(R.id.button2);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);

            }
        });
    }

    protected void onResume()
    {
        super.onResume();
        Log.i(email, "In onResume");
    }
    protected void onStart()
    {
        super.onStart();
        Log.i(email, "In onStart");
    }

    protected void onPause()
    {
        super.onPause();
        Log.i(email, "In onPause");
    }

    protected void onStop()
    {
        super.onStop();
        Log.i(email, "In onStop");
    }

    protected void onDestroy()
    {
        super.onDestroy();
        Log.i(email, "In onDestroy");
    }
}
