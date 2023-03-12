package com.magdenbt.collectionsbenchmark.UI.Stat;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.magdenbt.collectionsbenchmark.CollectionsType;
import com.magdenbt.collectionsbenchmark.DataModel;
import com.magdenbt.collectionsbenchmark.DataRepository;

import java.util.ArrayList;
import java.util.Objects;

public class DataViewModel extends AndroidViewModel {

    private ArrayList<MutableLiveData<DataModel>> mutableLiveData = new ArrayList<>();
    private final CollectionsType collectionsType;


    public DataViewModel(@NonNull Application application, CollectionsType collectionsType) {
        super(application);
        this.collectionsType = collectionsType;
    }

    public ArrayList<MutableLiveData<DataModel>> getMutableLiveData() {
        if(mutableLiveData.isEmpty())  mutableLiveData = DataRepository.getModels(getApplication().getApplicationContext(), collectionsType);
        return mutableLiveData;
    }


    public void startBenchmark(int sizeCollection, int amountElements){
        for (MutableLiveData<DataModel> mutableLiveDatum : mutableLiveData) {
            try {
                Objects.requireNonNull(mutableLiveDatum.getValue()).startBenchmark(sizeCollection, amountElements);
            }catch (NullPointerException e){
                Log.e("RVStatViewModel", mutableLiveDatum.toString() + " has NULL value");
            }
        }
    }
}
