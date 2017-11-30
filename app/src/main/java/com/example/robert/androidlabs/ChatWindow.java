package com.example.robert.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends Activity {

    ListView listView;
    EditText sendText;
    Button sendButton;
    final ArrayList<String> chatMessage = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        listView = findViewById(R.id.listView);
        sendText = findViewById(R.id.editText);
        sendButton = findViewById(R.id.button3);
        final ChatAdapter messageAdapter = new ChatAdapter( this );
        listView.setAdapter (messageAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageAdapter.notifyDataSetChanged();
                String text = sendText.getText().toString();
                chatMessage.add(text);
                sendText.setText("");
            }
        });
    }

    private class ChatAdapter extends ArrayAdapter<String>{
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() //how many items to display
        {
            return chatMessage.size();
        }

        public String getItem(int position) {
            return chatMessage.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {

            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result;

            if(position%2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);


            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(   getItem(position)  ); // get the string at position
            return result;

        }
    }

}