package com.example.topcelebritieslistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase database;

    //Table Name
    private static final String TABLE_NAME = "CELEBRITIES";

    //Table columns
    public static final String _ID = "_id";
    public static final String FIRST_NAME ="first_name";
    public static final String LAST_NAME = "last_name";
    public static final String DATE = "Date";

    //DataBase Information
    static final String DB_NAME = "Celebrities.DB";
    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + _ID
            +" INTEGER PRIMARY KEY AUTOINCREMENT , " + FIRST_NAME + " TEXT NOT NULL, "+ LAST_NAME +
            " TEXT NOT NULL, " + DATE + " TEXT);";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void open() throws SQLException {
        database = this.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public void add(Celebrity celebrity){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME, celebrity.getName());
        contentValues.put(LAST_NAME, celebrity.getLastName());
        contentValues.put(DATE, celebrity.getDate());

        database.insert(TABLE_NAME,null, contentValues);

    }

    public Cursor getAllCelebrities(){
        String[] projection = {_ID,FIRST_NAME,LAST_NAME,DATE};
        Cursor cursor = database.query(TABLE_NAME,projection,null,null,null,null,null,null);
        return cursor;
    }

    public int update(Celebrity pCelebrity) {
        long _id= pCelebrity.getId();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME, pCelebrity.getName());
        contentValues.put(LAST_NAME,pCelebrity.getLastName());
        contentValues.put(DATE, pCelebrity.getDate());
        return database.update(TABLE_NAME, contentValues, _ID + " = " + _id, null);
    }
}
