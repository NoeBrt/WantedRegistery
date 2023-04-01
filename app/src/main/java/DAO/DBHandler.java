package DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Model.WantedPerson;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    //change version when upgraded
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Form.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE " + DBContract.Form.TABLE_NAME + " (" +
                DBContract.Form._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBContract.Form.COLUMN_PHOTO + " BLOB," +
                DBContract.Form.COLUMN_NAME + " TEXT," +
                DBContract.Form.COLUMN_SUBJECT + " TEXT,"+
                DBContract.Form.COLUMN_UID + " TEXT)";

        db.execSQL(query);
    }

    public void insertWanted(byte[] photo, String name, String subject,String uid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues row = new ContentValues();

        row.put(DBContract.Form.COLUMN_PHOTO, photo);
        row.put(DBContract.Form.COLUMN_NAME, name);
        row.put(DBContract.Form.COLUMN_SUBJECT, subject);
        row.put(DBContract.Form.COLUMN_UID, uid);
        db.insert(DBContract.Form.TABLE_NAME, null, row);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + DBContract.Form.TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }



    public ArrayList<WantedPerson> select(int limit) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DBContract.Form.TABLE_NAME;
        if (limit > 0) query = query + " LIMIT " + limit;
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<WantedPerson> responses = new ArrayList<>();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") byte[] photo = cursor.getBlob(cursor.getColumnIndex(DBContract.Form.COLUMN_PHOTO));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_NAME));
            @SuppressLint("Range") String subject = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_SUBJECT));
            @SuppressLint("Range") String uid = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_UID));
            WantedPerson response = new WantedPerson(photo, name, subject,uid);
            responses.add(response);
        }

        cursor.close();
        return responses;
    }

    public void deleteForm (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBContract.Form.TABLE_NAME, null, null);
    }

    public void deleteFormID (int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = DBContract.Form._ID + " LIKE ?";
        String[] selectionArgs = {Integer.toString(id)};
        db.delete(DBContract.Form.TABLE_NAME, selection, selectionArgs);
     }
}

