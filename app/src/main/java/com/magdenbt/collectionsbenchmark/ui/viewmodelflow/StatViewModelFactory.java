package com.magdenbt.collectionsbenchmark.ui.viewmodelflow;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.magdenbt.collectionsbenchmark.CollectionsType;
import com.magdenbt.collectionsbenchmark.modelflow.StatRepository;

public class StatViewModelFactory implements ViewModelProvider.Factory {

    private final CollectionsType collectionsType;
    private final StatRepository statRepository;
    private final Application application;


    public StatViewModelFactory(Application application, StatRepository statRepository, CollectionsType collectionsType) {
        this.collectionsType = collectionsType;
        this.statRepository = statRepository;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StatViewModel.class))
            return (T) new StatViewModel(application, statRepository, collectionsType);
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
