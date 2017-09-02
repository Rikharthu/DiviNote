package com.example.uberv.divinote.presentation.di.modules;

import android.app.Activity;

import com.example.uberv.divinote.presentation.di.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph
 */
@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    /**
     * Expose the activity to dependents in the graph
     */
    @Provides
    @PerActivity
    Activity getActivity() {
        return mActivity;
    }
}
