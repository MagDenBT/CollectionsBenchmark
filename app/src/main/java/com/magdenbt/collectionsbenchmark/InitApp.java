package com.magdenbt.collectionsbenchmark;

import android.app.Application;
import com.magdenbt.collectionsbenchmark.di.AppComponent;
import com.magdenbt.collectionsbenchmark.di.DaggerAppComponent;

public class InitApp extends Application {


    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        if(appComponent == null) initializeAppComponent();
        return appComponent;
    }

    private void initializeAppComponent() {
       appComponent = DaggerAppComponent.factory().create(getApplicationContext(),this);
    }

}
