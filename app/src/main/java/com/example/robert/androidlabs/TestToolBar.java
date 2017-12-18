package com.example.robert.androidlabs;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestToolBar extends AppCompatActivity {
    public static final String snackString = "SNACK_STRING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tool_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "I am hungry for a snack...bar...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatedLayout);
        final Snackbar snack = Snackbar.make(coordinatorLayout, "You Selected Item 1", Snackbar.LENGTH_LONG);

        switch (item.getItemId()) {
            case R.id.action_one:
                snack.show();
                break;

            case R.id.action_two:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Do you want to go back?");
                builder.setCancelable(false)

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // What to do on Accept
                                startActivity(new Intent(TestToolBar.this, StartActivity.class));
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // What to do on Cancel
                                dialog.dismiss();
                            }
                        });
                builder.create().show();

                break;

            case R.id.action_three:
                final SharedPreferences snackPreference = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
                AlertDialog.Builder editBuilder = new AlertDialog.Builder(this);
                // Get the layout inflater
                LayoutInflater inflater = this.getLayoutInflater();
                View view = inflater.inflate(R.layout.text_edit_layout, null);
                final EditText editSnack = (EditText) view.findViewById(R.id.snackEdit);
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                editBuilder.setView(view)
                        // Add action buttons
                        .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String snackText = editSnack.getText().toString();
                                SharedPreferences.Editor editor = snackPreference.edit();
                                editor.putString(snackString, snackText);
                                final Snackbar editedSnack = Snackbar.make(coordinatorLayout, snackText, Snackbar.LENGTH_LONG);
                                editedSnack.show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                editBuilder.create().show();
                break;

            case R.id.action_four:
                Toast t = Toast.makeText(this, "Version 1.0, by Robert Wright", Toast.LENGTH_LONG);
                t.show();
        }
        return true;
    }


}
