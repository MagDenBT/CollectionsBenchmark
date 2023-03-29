package com.magdenbt.collectionsbenchmark.di.mainactivity;

import androidx.fragment.app.FragmentActivity;

import com.magdenbt.collectionsbenchmark.ui.viewflow.MainActivity;
import com.magdenbt.collectionsbenchmark.di.SharedCollSizeViewModelModule;

import dagger.Binds;
import dagger.Module;

@Module(includes = SharedCollSizeViewModelModule.class)
public abstract class MainModule {

    @Binds
    public abstract FragmentActivity bindFragmentActivity(MainActivity activity);


}
