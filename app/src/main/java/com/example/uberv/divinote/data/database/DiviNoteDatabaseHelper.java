package com.example.uberv.divinote.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.uberv.divinote.data.models.NoteEntity;

import java.util.Random;

import timber.log.Timber;

public class DiviNoteDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_NOTES_TABLE = "CREATE TABLE " +
            DiviNoteDBContract.NoteTable.TABLE_NAME + " (" +
            DiviNoteDBContract.NoteTable._ID + " INTEGER PRIMARY KEY," +             // define a primary key
            DiviNoteDBContract.NoteTable.COLUMN_NAME_CREATED_AT + " INTEGER," +
            DiviNoteDBContract.NoteTable.COLUMN_NAME_UPDATED_AT + " INTEGER," +
            DiviNoteDBContract.NoteTable.COLUMN_NAME_REMIND_AT + " INTEGER," +
            DiviNoteDBContract.NoteTable.COLUMN_NAME_TITLE + " TEXT," +
            DiviNoteDBContract.NoteTable.COLUMN_NAME_STATUS + " TEXT," +
            DiviNoteDBContract.NoteTable.COLUMN_NAME_DESCRIPTION + " TEXT" + " )";
    public static final String DROP_NOTES_TABLE = "DROP TABLE IF EXISTS " + DiviNoteDBContract.NoteTable.TABLE_NAME;

    public DiviNoteDatabaseHelper(Context context) {
        super(context, DiviNoteDBContract.DATABASE_NAME, null, DiviNoteDBContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_NOTES_TABLE);
        // FIXME for debug
        seedDatabase(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Timber.d("Upgrading from version " + oldVersion + " to " + newVersion);
    }

    private void seedDatabase(SQLiteDatabase db) {
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            NoteEntity entity = new NoteEntity();
            entity.setTitle("Title " + i);
            entity.setCreatedAt(System.currentTimeMillis());
            entity.setUpdatedAt(System.currentTimeMillis());
            entity.setRemindAt(System.currentTimeMillis() + 1000 * 60 * 60 * (i + 1));
            entity.setDescription("Description " + i);

            int x = rnd.nextInt(3);
            switch (x) {
                case 0:
                    entity.setStatus(NoteEntity.STATUS_COMPLETED);
                    break;
                case 1:
                    entity.setStatus(NoteEntity.STATUS_TODO);
                    break;
                case 2:
                default:
                    entity.setStatus(NoteEntity.STATUS_FINISHED);
                    break;
            }

            db.insert(DiviNoteDBContract.NoteTable.TABLE_NAME, null, noteToContentsValues(entity));
        }
    }

    public static ContentValues noteToContentsValues(NoteEntity note) {
        ContentValues values = new ContentValues();
        values.put(DiviNoteDBContract.NoteTable.COLUMN_NAME_CREATED_AT, note.getCreatedAt());
        values.put(DiviNoteDBContract.NoteTable.COLUMN_NAME_UPDATED_AT, note.getUpdatedAt());
        values.put(DiviNoteDBContract.NoteTable.COLUMN_NAME_REMIND_AT, note.getRemindAt());
        values.put(DiviNoteDBContract.NoteTable.COLUMN_NAME_STATUS, note.getStatus());
        values.put(DiviNoteDBContract.NoteTable.COLUMN_NAME_TITLE, note.getTitle());
        values.put(DiviNoteDBContract.NoteTable.COLUMN_NAME_DESCRIPTION, note.getDescription());
        return values;
    }
}
