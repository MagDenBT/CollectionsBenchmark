package com.magdenbt.collectionsbenchmark.UI.Stat;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.magdenbt.collectionsbenchmark.CollectionsType;
import com.magdenbt.collectionsbenchmark.StatModel;
import com.magdenbt.collectionsbenchmark.StatRepository;

import java.util.List;
import java.util.Objects;

public class StatVM extends AndroidViewModel {

    private List<LiveData<StatModel>> statModelsLD;
    private final CollectionsType collectionsType;
    private final StatRepository statRepository;

    public StatVM(@NonNull Application application, CollectionsType collectionsType) {
        super(application);
        this.collectionsType = collectionsType;
        statRepository = new StatRepository(getApplication().getApplicationContext());
    }

    public List<LiveData<StatModel>> getStatModelsLD() {
        if(statModelsLD == null)  statModelsLD = statRepository.getModels( collectionsType);
        return statModelsLD;
    }


    public void startBenchmark(int sizeCollection, int amountElements){
        for (LiveData<StatModel> mutableLiveDatum : statModelsLD) {
            try {
                Objects.requireNonNull(mutableLiveDatum.getValue()).startBenchmark(sizeCollection, amountElements);
            }catch (NullPointerException e){
                Log.e("RVStatViewModel", mutableLiveDatum.toString() + " has NULL value");
            }
        }
    }
}
