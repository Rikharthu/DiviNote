package com.example.uberv.divinote.presentation.di.components;

import android.app.Activity;

import com.example.uberv.divinote.presentation.di.modules.ActivityModule;
import com.example.uberv.divinote.presentation.di.scopes.PerActivity;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 * <p>
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
}
