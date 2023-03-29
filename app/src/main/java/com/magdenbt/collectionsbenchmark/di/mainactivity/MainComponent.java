package com.magdenbt.collectionsbenchmark.di.mainactivity;



import com.magdenbt.collectionsbenchmark.ui.viewflow.MainActivity;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent(modules = {MainModule.class})
public interface MainComponent {

    @Subcomponent.Factory
    interface Factory{
        MainComponent create(@BindsInstance MainActivity activity);
    }

    void inject(MainActivity activity);



}
