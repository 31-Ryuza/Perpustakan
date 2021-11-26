package com.example.py7.perpustakaan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBHalper1 extends SQLiteOpenHelper {
    public static final String database_name = "db_login";
    public static final String table_name = "table_login";

    public static final String rov_id = "_id";
    public static final String rov_username = "Username";
    public static final String rov_password = "Password";

    private SQLiteDatabase db;

    public DBHalper1(Context context) {
        super(context, database_name, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name + "(" + rov_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + rov_username + " TEXT," + rov_password + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
    }

    public void insertData(ContentValues values) {
        db.insert(table_name, null, values);
    }

    public boolean checkUser(String username, String password) {
        String[] columns = {rov_id};
        SQLiteDatabase db  = getReadableDatabase();
        String selection = rov_username + "=?" + " and " + rov_password + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(table_name, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count>0)
            return true;
        else
            return false;
    }

}
