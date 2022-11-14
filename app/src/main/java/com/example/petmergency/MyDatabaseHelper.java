package com.example.petmergency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "medications.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "medication_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MEDNAME = "medication_name";
    private static final String COLUMN_DOSAGE = "medication_dosage";
    private static final String COLUMN_DATE = "medication_date";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_MEDNAME + " TEXT, " +
                        COLUMN_DOSAGE + " INTEGER, " +
                        COLUMN_DATE + " DATETIME);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    void addMedicine(String name, int dosage, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_MEDNAME, name);
        cv.put(COLUMN_DOSAGE, dosage);
        cv.put(COLUMN_DATE, date);

        long res = db.insert(TABLE_NAME, null, cv);
        if(res == -1){
            Toast.makeText(context, "FAILED",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Success",Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){

        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    void updateData(String row_id, String name, int dosage, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MEDNAME, name);
        contentValues.put(COLUMN_DOSAGE, dosage);
        contentValues.put(COLUMN_DATE, date);

        long res = db.update(TABLE_NAME, contentValues, "_id=?", new String[]{row_id});
        if(res == -1){
            Toast.makeText(context, "FAILED TO UPDATE", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "UPDATED", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteRow(String row_id){

        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(res == -1){
            Toast.makeText(context, "FAILED TO DELETE", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "DELETED", Toast.LENGTH_SHORT).show();
        }
    }
}
