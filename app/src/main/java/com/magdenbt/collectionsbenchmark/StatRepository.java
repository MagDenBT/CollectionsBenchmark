package com.magdenbt.collectionsbenchmark;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class StatRepository {

    public static ArrayList<MutableLiveData<StatModel>> getModels(Context context, CollectionsType collectionsType){
        ArrayList<MutableLiveData<StatModel>> models = new ArrayList<>();
        for (OperationTypes operationType: OperationTypes.values()
        ) {
            if(operationType.collectionsType == collectionsType){
                MutableLiveData<StatModel> mutableLiveData = new MutableLiveData<>();
                mutableLiveData.setValue(new StatModel(context,operationType, mutableLiveData::setValue));
                models.add(mutableLiveData);
            }
        }
        return models;
    }

}
