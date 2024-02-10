package com.example.notetaskpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notesDB";
    private static final int DATABASE_VERSION = 1;
    public static final String NOTES_TABLE = "notes";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESC = "description";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + NOTES_TABLE + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_DATE + " TEXT, "
                    + COLUMN_TITLE + " TEXT, "
                    + COLUMN_DESC + " TEXT);";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



//    public List<Note> getAllItems() {
//        List<Note> noteList = new ArrayList<>();
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor cursor = db.query(
//                NOTES_TABLE,
//                new String[]{COLUMN_DATE, COLUMN_TITLE,COLUMN_DESC},
//                null,
//                null,
//                null,
//                null,
//                null
//        );
//        if (cursor != null){
//            try{
//                if(cursor.moveToFirst()){
//                    int dateIdx = cursor.getColumnIndexOrThrow(COLUMN_DATE);
//                    int titleIdx = cursor.getColumnIndexOrThrow(COLUMN_TITLE);
//                    int descIdx = cursor.getColumnIndexOrThrow(COLUMN_DESC);
//
//                    do{
//                        String date = cursor.getString(dateIdx);
//                        String title = cursor.getString(titleIdx);
//                        String desc = cursor.getString(descIdx);
//
//                        noteList.add(new Note(date, title, desc));
//                    }while(cursor.moveToNext());
//                }else{
//                    Log.d("DBQuery", "No Data Found");
//                }
//            }catch (Exception e){
//                Log.e("DBQuery", "Error! Cannot retreive data", e);
//            }finally{
//                cursor.close();
//            }
//        }else{
//            Log.e("DBQuery","Null Cursor");
//        }
//        db.close();
//        return noteList;
//    }

    public List<Note> getAllItems() {
        List<Note> noteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query(
                    NOTES_TABLE,
                    new String[]{COLUMN_ID, COLUMN_DATE, COLUMN_TITLE, COLUMN_DESC},
                    null,
                    null,
                    null,
                    null,
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                int idIdx = cursor.getColumnIndexOrThrow(COLUMN_ID);
                int dateIdx = cursor.getColumnIndexOrThrow(COLUMN_DATE);
                int titleIdx = cursor.getColumnIndexOrThrow(COLUMN_TITLE);
                int descIdx = cursor.getColumnIndexOrThrow(COLUMN_DESC);

                do {
                    int id = Integer.parseInt(cursor.getString(idIdx));
                    String date = cursor.getString(dateIdx);
                    String title = cursor.getString(titleIdx);
                    String desc = cursor.getString(descIdx);

                    noteList.add(new Note(id, date, title, desc));
                } while (cursor.moveToNext());
            } else {
                Log.d("DBQuery", "No Data Found");
            }
        } catch (Exception e) {
            Log.e("DBQuery", "Error! Cannot retrieve data", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            //db.close();
        }

        return noteList;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public void deleteNote(long noteId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NOTES_TABLE, COLUMN_ID + " = ?", new String[]{String.valueOf(noteId)});
        db.close();
    }


}
