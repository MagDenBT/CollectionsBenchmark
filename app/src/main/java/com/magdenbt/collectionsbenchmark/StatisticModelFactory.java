package com.magdenbt.collectionsbenchmark;

import android.content.Context;

import androidx.lifecycle.Observer;

import java.util.ArrayList;

public class StatisticModelFactory {

    public static ArrayList<StatisticModel> getModels(Context context,CollectionsType collectionsType, Observer<StatisticModel> observer){
        ArrayList<StatisticModel> models = createModels(context, collectionsType, observer);
        Worker.getInstance().addListeners(models);
        return models;
    }

    private static ArrayList<StatisticModel> createModels(Context context, CollectionsType collectionsType,Observer<StatisticModel> observer){

        ArrayList<StatisticModel> models = new ArrayList<>();
        for (Class collectionClass:
                collectionsType.supportClasses()){
            for (OperationTypes operationType: OperationTypes.values()
            ) {
                if(operationType.collectionsType == collectionsType){
                    models.add(new StatisticModel(context,operationType,collectionClass, observer));
                }
            }
        }
        return models;
    }


}
