package com.example.robert.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static java.sql.Types.INTEGER;

/**
 * Created by Robert on 2017-10-17.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    public final static String name = "MyTable";
    private static final String DATABASE_NAME = "Messages.db";
    private static final int VERSION_NUM = 3;
    private static final String KEY_ID = "id";
    private static final String KEY_MESSAGE = "message";

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getName() {
        return name;
    }

    public static String getKeyMessage() {
        return KEY_MESSAGE;
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String createDatabase = "CREATE TABLE " + name + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_MESSAGE + " TEXT" + " ) ";

        db.execSQL(createDatabase);
        Log.i("ChatDatabaseHelper", "Calling onCreate");
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + name);

        onCreate(db);

        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
    }

}
