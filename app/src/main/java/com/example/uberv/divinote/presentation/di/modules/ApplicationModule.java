package com.example.uberv.divinote.presentation.di.modules;

import android.content.Context;

import com.example.uberv.divinote.DiviNoteApp;
import com.example.uberv.divinote.data.cache.NoteCache;
import com.example.uberv.divinote.data.cache.NoteCacheImpl;
import com.example.uberv.divinote.data.database.DiviNoteDatabaseHelper;
import com.example.uberv.divinote.data.executor.JobExecutor;
import com.example.uberv.divinote.data.repository.NoteDataRepository;
import com.example.uberv.divinote.domain.executor.PostExecutionThread;
import com.example.uberv.divinote.domain.executor.ThreadExecutor;
import com.example.uberv.divinote.domain.repository.NoteRepository;
import com.example.uberv.divinote.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final DiviNoteApp mApplication;

    public ApplicationModule(DiviNoteApp application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    NoteCache provideUserCache(NoteCacheImpl noteCache) {
        return noteCache;
    }

    @Provides
    @Singleton
    NoteRepository provideUserRepository(NoteDataRepository userDataRepository) {
        return userDataRepository;
    }

    @Provides
    @Singleton
    DiviNoteDatabaseHelper provideDatabaseHelper(Context context) {
        return new DiviNoteDatabaseHelper(mApplication.getApplicationContext());
    }
}
