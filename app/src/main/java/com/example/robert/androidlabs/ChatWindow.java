package com.example.robert.androidlabs;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ChatWindow extends Activity {

    ListView listView;
    EditText sendText;
    Button sendButton;
    Boolean isTablet;

    public final ArrayList<String> chatMessage = new ArrayList<>();
    public static Cursor results;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        listView = findViewById(R.id.listView);
        sendText = findViewById(R.id.editText);
        sendButton = findViewById(R.id.button3);

        isTablet = findViewById(R.id.fragmentFrame) != null;
        final MessageFragment messageFragment = new MessageFragment();

        final Bundle info = new Bundle();
        final ChatAdapter messageAdapter = new ChatAdapter( this );
        listView.setAdapter (messageAdapter);

        final ChatDatabaseHelper storedMessages = new ChatDatabaseHelper(this);
        final SQLiteDatabase db = storedMessages.getWritableDatabase();

        results = db.query(false, storedMessages.getName(), new String[] {storedMessages.getKeyMessage(), ChatDatabaseHelper.getKeyId()},
                null, null , null, null, null, null);


        if (results.moveToFirst()){
            do {
                chatMessage.add(results.getString(0));
            } while (results.moveToNext());
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if(isTablet){
                    messageFragment.setArguments(info);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.fragmentFrame, messageFragment);
                    fragmentTransaction.commit();
                } else {
                    Intent phoneIntent = new Intent(ChatWindow.this, MessageDetails.class);
                    String passedMessage = messageAdapter.getItem(position);
                    Long passedID = messageAdapter.getItemId(position);
                    info.putString("Message", passedMessage);
                    info.putString("Key", passedID.toString());
                    phoneIntent.putExtras(info);
                    startActivity(phoneIntent);
                }

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues newData = new ContentValues();
                messageAdapter.notifyDataSetChanged();
                String text = sendText.getText().toString();
                chatMessage.add(text);
                newData.put(storedMessages.getKeyMessage(), text);
                db.insert(storedMessages.getName(), "", newData);
                sendText.setText("");
            }
        });
    }

    private class ChatAdapter extends ArrayAdapter<String>{
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public void remove(int position){
            chatMessage.remove(position);
            notifyDataSetChanged();
        }

        public int getCount() //how many items to display
        {
            return chatMessage.size();
        }

        public String getItem(int position) {

            return chatMessage.get(position);
        }

        @Override
        public long getItemId(int position) {
            results.moveToPosition(position);
            return Long.parseLong(results.getString(results.getColumnIndex(ChatDatabaseHelper.getKeyId())));
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 10) {
            if (resultCode == Activity.RESULT_OK){
                chatMessage.remove(data);
            }
        }
    }

    public void onDestroy() {

        super.onDestroy();

    }

}