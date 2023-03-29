package com.magdenbt.collectionsbenchmark.di.fragments;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.magdenbt.collectionsbenchmark.CollectionsType;
import com.magdenbt.collectionsbenchmark.di.SharedCollSizeViewModelModule;
import com.magdenbt.collectionsbenchmark.modelflow.StatRepository;
import com.magdenbt.collectionsbenchmark.ui.viewmodelflow.StatViewModelFactory;
import com.magdenbt.collectionsbenchmark.ui.viewflow.ViewPagerFragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = SharedCollSizeViewModelModule.class)
public abstract class ViewPagerFragmentModule {

    @Binds
    public abstract ViewModelStoreOwner bindViewModelStoreOwner(ViewPagerFragment vpFragment);


    @Provides
    public static ViewModelProvider provideViewModelProvider(ViewModelStoreOwner viewModelStoreOwner, StatRepository statRepository, Application application, CollectionsType collectionsType){
        return new ViewModelProvider(viewModelStoreOwner, new StatViewModelFactory(application, statRepository, collectionsType));
    }
}
