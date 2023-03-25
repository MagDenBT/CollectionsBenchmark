package com.magdenbt.collectionsbenchmark.di;

import android.app.Application;
import android.content.Context;


import com.magdenbt.collectionsbenchmark.di.Fragments.VPFragmentComponent;
import com.magdenbt.collectionsbenchmark.di.MainActivity.MainComponent;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;


@Singleton
@Component(modules = {AppSubcomponents.class})
public interface AppComponent {

    @Component.Factory
    interface Factory{
        AppComponent create(@BindsInstance Context context, @BindsInstance Application application);
    }

    MainComponent.Factory mainComponent();
    VPFragmentComponent.Builder VPFragmentComponentBuilder();

}
