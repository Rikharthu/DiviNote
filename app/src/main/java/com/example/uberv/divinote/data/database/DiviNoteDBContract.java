package com.example.uberv.divinote.data.database;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public abstract class DiviNoteDBContract {

    // Content Provider info
    public static final String CONTENT_AUTHORITY = "com.example.uberv.divinote";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_NOTE = "note";

    // Database Info
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "divinote_database";

    private DiviNoteDBContract() {
    }

    public static class NoteTable implements BaseColumns {
        // Provider stuff
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_NOTE).build();
        // for multiple rows
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_URI + "/" + PATH_NOTE;
        // for single item
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_NOTE;

        // Define a function to build a URI to find a specific note by it's identifier
        public static Uri buildNoteUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        // Table Name
        public static final String TABLE_NAME = "notes";
        // Notes Table Columns
        public static final String COLUMN_NAME_CREATED_AT = "created_at";
        public static final String COLUMN_NAME_UPDATED_AT = "updated_at";
        public static final String COLUMN_NAME_REMIND_AT = "remind_at";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }
}
