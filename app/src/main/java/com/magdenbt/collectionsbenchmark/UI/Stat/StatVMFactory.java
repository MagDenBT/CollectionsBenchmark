package com.magdenbt.collectionsbenchmark.UI.Stat;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.magdenbt.collectionsbenchmark.CollectionsType;

public class StatVMFactory implements ViewModelProvider.Factory{

    private final CollectionsType collectionsType;
    private final Application application;

    public StatVMFactory(Application application, CollectionsType collectionsType){
        this.collectionsType = collectionsType;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(StatVM.class)) return (T) new StatVM(application, collectionsType );
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
