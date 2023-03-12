package com.magdenbt.collectionsbenchmark;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class DataRepository {

    public static ArrayList<MutableLiveData<DataModel>> getModels(Context context, CollectionsType collectionsType){
        ArrayList<MutableLiveData<DataModel>> models = new ArrayList<>();
        for (OperationTypes operationType: OperationTypes.values()
        ) {
            if(operationType.collectionsType == collectionsType){
                MutableLiveData<DataModel> mutableLiveData = new MutableLiveData<>();
                mutableLiveData.setValue(new DataModel(context,operationType, mutableLiveData::setValue));
                models.add(mutableLiveData);
            }
        }
        return models;
    }

}
