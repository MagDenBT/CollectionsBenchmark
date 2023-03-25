package com.magdenbt.collectionsbenchmark.di.MainActivity;

import androidx.fragment.app.FragmentActivity;

import com.magdenbt.collectionsbenchmark.UI.ViewFlow.MainActivity;
import com.magdenbt.collectionsbenchmark.di.SharedCollSizeVMModule;

import dagger.Binds;
import dagger.Module;

@Module(includes = SharedCollSizeVMModule.class)
public abstract class MainModule {

    @Binds
    public abstract FragmentActivity bindFragmentActivity(MainActivity activity);


}
