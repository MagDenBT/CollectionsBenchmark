package com.magdenbt.collectionsbenchmark.UI.Stat;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.magdenbt.collectionsbenchmark.CollectionsType;
import com.magdenbt.collectionsbenchmark.StatModel;
import com.magdenbt.collectionsbenchmark.StatRepository;

import java.util.ArrayList;
import java.util.Objects;

public class StatVM extends AndroidViewModel {

    private ArrayList<MutableLiveData<StatModel>> mutableLiveData = new ArrayList<>();
    private final CollectionsType collectionsType;


    public StatVM(@NonNull Application application, CollectionsType collectionsType) {
        super(application);
        this.collectionsType = collectionsType;
    }

    public ArrayList<MutableLiveData<StatModel>> getMutableLiveData() {
        if(mutableLiveData.isEmpty())  mutableLiveData = StatRepository.getModels(getApplication().getApplicationContext(), collectionsType);
        return mutableLiveData;
    }


    public void startBenchmark(int sizeCollection, int amountElements){
        for (MutableLiveData<StatModel> mutableLiveDatum : mutableLiveData) {
            try {
                Objects.requireNonNull(mutableLiveDatum.getValue()).startBenchmark(sizeCollection, amountElements);
            }catch (NullPointerException e){
                Log.e("RVStatViewModel", mutableLiveDatum.toString() + " has NULL value");
            }
        }
    }
}
