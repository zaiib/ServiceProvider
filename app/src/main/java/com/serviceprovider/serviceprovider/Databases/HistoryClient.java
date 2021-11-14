package com.serviceprovider.serviceprovider.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class HistoryClient extends SQLiteOpenHelper {
    private static final String TAG = "DB";

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Client_History";

    // table name
    private static final String TABLE_USER = "client_task";

    //Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_TASKER = "tasker";
    private static final String KEY_BUDGET = "budget";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_DATE = "date";
    private static final String KEY_LOCATION = "location";

    public HistoryClient(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT," + KEY_DESCRIPTION + " TEXT,"
                + KEY_TASKER + " TEXT, " + KEY_BUDGET + " TEXT," + KEY_ADDRESS + " TEXT," + KEY_PHONE + " TEXT,"
                + KEY_DATE + " TEXT," + KEY_LOCATION + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }
    public void addUser(String title, String description, String taskerPerson, String budget, String address, String phone, String date, String location) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title); // Name
        values.put(KEY_DESCRIPTION, description); // Email
        values.put(KEY_TASKER, taskerPerson); // Email
        values.put(KEY_BUDGET, budget); // Email
        values.put(KEY_ADDRESS, address); // Email
        values.put(KEY_PHONE, phone); // Email
        values.put(KEY_DATE, date); // Created At
        values.put(KEY_LOCATION, location); // Created At

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
        //Toast.makeText(context, "New task inserted into sqlite: "+ id, Toast.LENGTH_SHORT).show();
    }
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("title", cursor.getString(1));
            user.put("description", cursor.getString(2));
            user.put("tasker", cursor.getString(3));
            user.put("budget", cursor.getString(4));
            user.put("address", cursor.getString(5));
            user.put("phone", cursor.getString(6));
            user.put("date", cursor.getString(7));
            user.put("location", cursor.getString(8));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }
    public Cursor alldata() {
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row

        return cursor;
    }
}
