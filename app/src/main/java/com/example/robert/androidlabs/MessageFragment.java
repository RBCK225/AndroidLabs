package com.example.robert.androidlabs;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.example.robert.androidlabs.R;

/**
 * Created by rob on 2017-12-05.
 */

public class MessageFragment extends Fragment {

    public MessageFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the xml file for the fragment
        View v = inflater.inflate(R.layout.fragmentdetails, null);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String messageValue = this.getArguments().getString("Message");
        String messageID = this.getArguments().getString("Key");
        TextView message = view.findViewById(R.id.messageView);
        TextView idValue = view.findViewById(R.id.messageID);
        message.setText(messageValue);
        idValue.setText(messageID);

        Button deleteButton = (Button) view.findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                getActivity().setResult(Activity.RESULT_OK, resultIntent);
            }
        });

    }
}
