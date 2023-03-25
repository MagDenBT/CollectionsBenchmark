package com.magdenbt.collectionsbenchmark.di.MainActivity;



import com.magdenbt.collectionsbenchmark.UI.ViewFlow.MainActivity;

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
