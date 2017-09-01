package com.example.uberv.divinote;

import android.app.Application;

import timber.log.Timber;

public class DiviNoteApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
