package com.example.robert.androidlabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends Activity {

    protected final String listActivity = "listActivity";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageButton camera;
    Switch mySwitch;
    CheckBox myCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

        camera = findViewById(R.id.imageButton);

        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        mySwitch = (Switch) findViewById(R.id.switch1);

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CharSequence onTtext = "Switch is On";
                CharSequence offText = "Switch is Off";
                int onDuration = Toast.LENGTH_SHORT;
                int offDuration = Toast.LENGTH_LONG;

                Toast onToast = Toast.makeText(ListItemsActivity.this, onTtext, onDuration); //this is the ListActivity
                Toast offToast = Toast.makeText(ListItemsActivity.this, offText, offDuration);

                if (mySwitch.isChecked() == true) {
                    onToast.show(); //display your message box
                } else {
                    offToast.show();
                }

            }
        });

        myCheck = (CheckBox) findViewById(R.id.checkBox2);

        myCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage("Would you like to finish the activity?") //Add a dialog message to strings.xml

                        .setTitle("Attention")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent resultIntent = new Intent(  );
                                resultIntent.putExtra("Response", "Here is my response");
                                setResult(Activity.RESULT_OK, resultIntent);
                                finish();

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                myCheck.setChecked(false);
                            }
                        })
                        .show();


            }
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            camera.setImageBitmap(imageBitmap);
        }
    }
}
