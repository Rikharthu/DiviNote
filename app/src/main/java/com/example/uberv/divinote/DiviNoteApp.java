package com.example.uberv.divinote;

import android.app.Application;

import com.example.uberv.divinote.presentation.di.components.ApplicationComponent;
import com.example.uberv.divinote.presentation.di.components.DaggerApplicationComponent;
import com.example.uberv.divinote.presentation.di.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

public class DiviNoteApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new DevelopmentTree());

        initializeInjector();
        initializeLeakDetection();
    }

    private void initializeInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
