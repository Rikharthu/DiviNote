package com.example.uberv.divinote.data.repository.datastore;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.uberv.divinote.data.database.DiviNoteDBContract;
import com.example.uberv.divinote.data.database.DiviNoteDatabaseHelper;
import com.example.uberv.divinote.data.models.NoteEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * {@link NoteDataStore} that uses {@link DiviNoteDatabaseHelper} to
 * store data in a local SQL database
 */
public class LocalNoteDataStore implements NoteDataStore {

    private DiviNoteDatabaseHelper mHelper;

    @Inject
    public LocalNoteDataStore(DiviNoteDatabaseHelper helper) {
        mHelper = helper;
    }

    @Override
    public Observable<List<NoteEntity>> noteEntityList() {
        SQLiteDatabase db = mHelper.getReadableDatabase();

        // query database
        Cursor cursor = db.query(
                DiviNoteDBContract.NoteTable.TABLE_NAME,
                null,   // columns
                null,   // selection
                null,   // selection arguments
                null,   // group by
                null,   // having
                null    // order by
        );

        // Parse database response
        List<NoteEntity> notes = new ArrayList<>();
        while (cursor.moveToNext()) {
            notes.add(extractNoteFromCursor(cursor));
        }

        // close cursor and database connection
        cursor.close();
        db.close();

        return Observable.just(notes);
    }

    private NoteEntity extractNoteFromCursor(Cursor cursor) {
        if (cursor != null || cursor.getCount() == 0) {
            try {
                NoteEntity note = new NoteEntity();

                note.setTitle(cursor.getString(
                        cursor.getColumnIndexOrThrow(DiviNoteDBContract.NoteTable.COLUMN_NAME_TITLE)
                ));
                note.setDescription(cursor.getString(
                        cursor.getColumnIndexOrThrow(DiviNoteDBContract.NoteTable.COLUMN_NAME_DESCRIPTION)
                ));
                note.setCreatedAt(cursor.getLong(
                        cursor.getColumnIndexOrThrow(DiviNoteDBContract.NoteTable.COLUMN_NAME_CREATED_AT)
                ));
                note.setUpdatedAt(cursor.getLong(
                        cursor.getColumnIndexOrThrow(DiviNoteDBContract.NoteTable.COLUMN_NAME_UPDATED_AT)
                ));
                note.setRemindAt(cursor.getLong(
                        cursor.getColumnIndexOrThrow(DiviNoteDBContract.NoteTable.COLUMN_NAME_REMIND_AT)
                ));
                note.setId(cursor.getLong(
                        cursor.getColumnIndexOrThrow(DiviNoteDBContract.NoteTable._ID)
                ));
                note.setStatus(cursor.getString(
                        cursor.getColumnIndexOrThrow(DiviNoteDBContract.NoteTable.COLUMN_NAME_STATUS)
                ));

                return note;
            } catch (IllegalArgumentException e) {
                // Cursor did not containt required field
                Timber.e(e, "Cursor did not contain required note column");
            }
        }

        return null;
    }

    @Override
    public Observable<NoteEntity> noteEntityDetails(int userId) {
        return null;
    }
}
