package com.example.root.demoapp;

import android.app.Application;

import com.example.root.demoapp.presentation.di.components.ApplicationComponent;
import com.example.root.demoapp.presentation.di.components.DaggerApplicationComponent;
import com.example.root.demoapp.presentation.di.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by root on 18/07/2017.
 */

public class MyApplication extends Application {
    private ApplicationComponent applicationComponent;
    private EventBus eventBus;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        initializeLeakDetection();

        eventBus = EventBus.getDefault();
    }

    private void initializeLeakDetection() {
        LeakCanary.install(this);
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    public EventBus getEventBus() {
        return this.eventBus;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
