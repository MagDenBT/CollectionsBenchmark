package com.magdenbt.collectionsbenchmark.UI.Stat;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.magdenbt.collectionsbenchmark.CollectionsType;

public class DataVMFactory implements ViewModelProvider.Factory{

    private final CollectionsType collectionsType;
    private final Application application;

    public DataVMFactory(Application application, CollectionsType collectionsType){
        this.collectionsType = collectionsType;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(DataViewModel.class)) return (T) new DataViewModel(application, collectionsType );
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
