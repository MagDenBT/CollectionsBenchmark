package com.magdenbt.collectionsbenchmark.modelflow;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.magdenbt.collectionsbenchmark.CollectionsType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatRepository {

    private final Context context;

    public StatRepository(Context context) {
        this.context = context;
    }

    public List<LiveData<StatModel>> getModels(CollectionsType collectionsType){

        ArrayList<LiveData<StatModel>> models =  new ArrayList<>();
        for (OperationTypes operationType: OperationTypes.values()
        ) {
            if(operationType.collectionsType == collectionsType){
                MutableLiveData<StatModel> mutableLiveData = new MutableLiveData<>();
                mutableLiveData.setValue(new StatModel(context,operationType, mutableLiveData::setValue));
                models.add(mutableLiveData);
            }
        }
        return  Collections.unmodifiableList(models);
    }

}
