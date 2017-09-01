package com.example.uberv.divinote.storage.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import timber.log.Timber;

public class DiviNoteDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_NOTES_TABLE = "CREATE TABLE " +
            DiviNoteDBContract.NoteTable.TABLE_NAME + " (" +
            DiviNoteDBContract.NoteTable._ID + " INTEGER PRIMARY KEY," +             // define a primary key
            DiviNoteDBContract.NoteTable.COLUMN_NAME_CREATED_AT + " INTEGER," +
            DiviNoteDBContract.NoteTable.COLUMN_NAME_UPDATED_AT + " INTEGER," +
            DiviNoteDBContract.NoteTable.COLUMN_NAME_REMIND_AT + " INTEGER," +
            DiviNoteDBContract.NoteTable.COLUMN_NAME_STATUS + " TEXT," +
            DiviNoteDBContract.NoteTable.COLUMN_NAME_TITLE + " TEXT," +
            DiviNoteDBContract.NoteTable.COLUMN_NAME_DESCRIPTION + " TEXT" + " )";
    public static final String DROP_NOTES_TABLE = "DROP TABLE IF EXISTS " + DiviNoteDBContract.NoteTable.TABLE_NAME;

    public DiviNoteDatabaseHelper(Context context){
        super(context, DiviNoteDBContract.DATABASE_NAME, null, DiviNoteDBContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Timber.d("Upgrading from version "+oldVersion+" to "+newVersion);
    }
}
