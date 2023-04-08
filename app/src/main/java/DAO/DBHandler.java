package DAO;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;

import Model.WantedPerson;

import java.io.File;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Data.db";

    public DBHandler(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE " + DBContract.Form.TABLE_NAME + " (" +
                DBContract.Form._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBContract.Form.COLUMN_PHOTO + " BLOB," +
                DBContract.Form.COLUMN_NAME + " TEXT," +
                DBContract.Form.COLUMN_SUBJECT + " TEXT," +
                DBContract.Form.COLUMN_UID + " TEXT," +
                DBContract.Form.COLUMN_WEIGHT + " TEXT," +
                DBContract.Form.COLUMN_DATE + " TEXT," +
                DBContract.Form.COLUMN_AGE + " TEXT," +
                DBContract.Form.COLUMN_HAIR + " TEXT," +
                DBContract.Form.COLUMN_EYES + " TEXT," +
                DBContract.Form.COLUMN_HEIGHT + " TEXT," +
                DBContract.Form.COLUMN_SEX + " TEXT," +
                DBContract.Form.COLUMN_RACE + " TEXT," +
                DBContract.Form.COLUMN_NATIONALITY + " TEXT," +
                DBContract.Form.COLUMN_MARKS + " TEXT," +
                DBContract.Form.COLUMN_NCIC + " TEXT," +
                DBContract.Form.COLUMN_REWARD + " TEXT," +
                DBContract.Form.COLUMN_ALIASES + " TEXT," +
                DBContract.Form.COLUMN_REMARKS + " TEXT," +
                DBContract.Form.COLUMN_CAUTION + " TEXT)";

        db.execSQL(query);

        String query2 = "CREATE TABLE " + DBContract.Form.TABLE_NAME2 + " (" +
                DBContract.Form._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBContract.Form.COLUMN_UID + " TEXT," +
                DBContract.Form.COLUMN_IMAGE + " TEXT)";

        db.execSQL(query2);
    }

    public void insertWanted(byte[] photo, ArrayList<Bitmap> images, String name, String subject, String uid, String weight, String dateOfBirthUsed, String age, String hair, String eyes, String height, String sex, String race, String nationality, String scarsAndMarks, String ncic, String reward, String aliases, String remarks, String caution, ContentResolver cr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(DBContract.Form.COLUMN_PHOTO, photo);
        row.put(DBContract.Form.COLUMN_NAME, name);
        row.put(DBContract.Form.COLUMN_SUBJECT, subject);
        row.put(DBContract.Form.COLUMN_UID, uid);
        row.put(DBContract.Form.COLUMN_WEIGHT, weight);
        row.put(DBContract.Form.COLUMN_DATE, dateOfBirthUsed);
        row.put(DBContract.Form.COLUMN_AGE, age);
        row.put(DBContract.Form.COLUMN_HAIR, hair);
        row.put(DBContract.Form.COLUMN_EYES, eyes);
        row.put(DBContract.Form.COLUMN_HEIGHT, height);
        row.put(DBContract.Form.COLUMN_SEX, sex);
        row.put(DBContract.Form.COLUMN_RACE, race);
        row.put(DBContract.Form.COLUMN_NATIONALITY, nationality);
        row.put(DBContract.Form.COLUMN_MARKS, scarsAndMarks);
        row.put(DBContract.Form.COLUMN_NCIC, ncic);
        row.put(DBContract.Form.COLUMN_REWARD, reward);
        row.put(DBContract.Form.COLUMN_ALIASES, aliases);
        row.put(DBContract.Form.COLUMN_REMARKS, remarks);
        row.put(DBContract.Form.COLUMN_CAUTION, caution);
        db.insert(DBContract.Form.TABLE_NAME, null, row);

        ContentValues row2 = new ContentValues();
        int i = 0;
        for (Bitmap image : images) {
            String imageURL = MediaStore.Images.Media.insertImage(cr, image, name + Integer.toString(i), "");
            row2.put(DBContract.Form.COLUMN_UID, uid);
            row2.put(DBContract.Form.COLUMN_IMAGE, imageURL);
            db.insert(DBContract.Form.TABLE_NAME2, null, row2);
            i++;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + DBContract.Form.TABLE_NAME;
        db.execSQL(query);
        onCreate(db);

        //DB2 PAS UTILE -> NON UTILISE
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
            @SuppressLint("Range") String weight = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_WEIGHT));
            @SuppressLint("Range") String dateOfBirthUsed = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_DATE));
            @SuppressLint("Range") String age = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_AGE));
            @SuppressLint("Range") String hair = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_HAIR));
            @SuppressLint("Range") String eyes = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_EYES));
            @SuppressLint("Range") String height = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_HEIGHT));
            @SuppressLint("Range") String sex = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_SEX));
            @SuppressLint("Range") String race = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_RACE));
            @SuppressLint("Range") String nationality = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_NATIONALITY));
            @SuppressLint("Range") String scarsAndMarks = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_MARKS));
            @SuppressLint("Range") String ncic = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_NCIC));
            @SuppressLint("Range") String reward = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_REWARD));
            @SuppressLint("Range") String aliases = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_ALIASES));
            @SuppressLint("Range") String remarks = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_REMARKS));
            @SuppressLint("Range") String caution = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_CAUTION));
            WantedPerson response = new WantedPerson(photo, selectImages(uid), name, subject, uid, weight, dateOfBirthUsed, age, hair, eyes, height, sex, race, nationality, scarsAndMarks, ncic, reward, aliases, remarks, caution);
            responses.add(response);
        }

        cursor.close();
        return responses;
    }

    public WantedPerson select(String uid) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DBContract.Form.TABLE_NAME + " WHERE " + DBContract.Form.COLUMN_UID + " = '" + uid + "'";
        Cursor cursor = db.rawQuery(query, null);

        WantedPerson wantedPerson = new WantedPerson();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") byte[] photo = cursor.getBlob(cursor.getColumnIndex(DBContract.Form.COLUMN_PHOTO));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_NAME));
            @SuppressLint("Range") String subject = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_SUBJECT));
            @SuppressLint("Range") String weight = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_WEIGHT));
            @SuppressLint("Range") String dateOfBirthUsed = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_DATE));
            @SuppressLint("Range") String age = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_AGE));
            @SuppressLint("Range") String hair = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_HAIR));
            @SuppressLint("Range") String eyes = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_EYES));
            @SuppressLint("Range") String height = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_HEIGHT));
            @SuppressLint("Range") String sex = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_SEX));
            @SuppressLint("Range") String race = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_RACE));
            @SuppressLint("Range") String nationality = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_NATIONALITY));
            @SuppressLint("Range") String scarsAndMarks = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_MARKS));
            @SuppressLint("Range") String ncic = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_NCIC));
            @SuppressLint("Range") String reward = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_REWARD));
            @SuppressLint("Range") String aliases = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_ALIASES));
            @SuppressLint("Range") String remarks = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_REMARKS));
            @SuppressLint("Range") String caution = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_CAUTION));
            wantedPerson = new WantedPerson(photo, selectImages(uid), name, subject, uid, weight, dateOfBirthUsed, age, hair, eyes, height, sex, race, nationality, scarsAndMarks, ncic, reward, aliases, remarks, caution);
        }

        cursor.close();
        return wantedPerson;
    }

    public ArrayList<Bitmap> selectImages(String uid) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DBContract.Form.TABLE_NAME2 + " WHERE "
                + DBContract.Form.COLUMN_UID + " = '" + uid + "'";
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Bitmap> responses = new ArrayList<>();
        int i = 0;
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String imagePath = cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_IMAGE));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inTempStorage = new byte[512];
            Bitmap image = BitmapFactory.decodeFile(imagePath, options);
            responses.add(image);
        }

        cursor.close();
        return responses;
    }

    public void deleteForm () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBContract.Form.TABLE_NAME, null, null);
        db.delete(DBContract.Form.TABLE_NAME2, null, null);
    }

    public void deleteFormUID(String uid){
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = DBContract.Form.COLUMN_UID + " LIKE ?";
        String[] selectionArgs = { uid };
        db.delete(DBContract.Form.TABLE_NAME, selection, selectionArgs);

        String selection2 = DBContract.Form.COLUMN_UID + " LIKE ?";
        db.delete(DBContract.Form.TABLE_NAME2, selection2, selectionArgs);
    }
}

