package com.magdenbt.collectionsbenchmark.ui.viewmodelflow;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.magdenbt.collectionsbenchmark.CollectionsType;
import com.magdenbt.collectionsbenchmark.modelflow.StatModel;
import com.magdenbt.collectionsbenchmark.modelflow.StatRepository;

import java.util.List;
import java.util.Objects;

public class StatViewModel extends AndroidViewModel {

    private List<LiveData<StatModel>> statModelsLD;
    private final CollectionsType collectionsType;
    private final StatRepository statRepository;

    public StatViewModel(@NonNull Application application, StatRepository statRepository, CollectionsType collectionsType) {
        super(application);
        this.collectionsType = collectionsType;
        this.statRepository = statRepository;
    }

    public List<LiveData<StatModel>> getStatModelsLD() {
        if (statModelsLD == null) statModelsLD = statRepository.getModels(collectionsType);
        return statModelsLD;
    }

    public void startBenchmark(int sizeCollection, int amountElements) {
        for (LiveData<StatModel> mutableLiveDatum : statModelsLD) {
            try {
                Objects.requireNonNull(mutableLiveDatum.getValue()).startBenchmark(sizeCollection, amountElements);
            } catch (NullPointerException e) {
                Log.e("RVStatViewModel", mutableLiveDatum.toString() + " has NULL value");
            }
        }
    }
}
