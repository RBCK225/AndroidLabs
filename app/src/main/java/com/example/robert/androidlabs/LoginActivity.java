package com.example.robert.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

    protected final String email = "logInName";
    protected Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final SharedPreferences userEmail = getSharedPreferences(email, 0);

        final TextView etxt = findViewById(R.id.textView2);
        etxt.setText(userEmail.getString("DefaultEmail", ""));
        loginButton = (Button) findViewById(R.id.button2);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedEamil = etxt.getText().toString();
                SharedPreferences.Editor editor = userEmail.edit();
                editor.putString("DefaultEmail", savedEamil);
                editor.commit();

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
