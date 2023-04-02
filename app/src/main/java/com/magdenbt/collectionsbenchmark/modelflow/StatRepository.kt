package com.magdenbt.collectionsbenchmark.modelflow

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magdenbt.collectionsbenchmark.CollectionsType
import javax.inject.Inject

class StatRepository @Inject constructor(val context: Context) {

    fun getModels(collectionsType: CollectionsType): List<LiveData<StatModel>> {
        val models = mutableListOf<LiveData<StatModel>>()
        for (operationTypes in OperationTypes.values()) {
            if (operationTypes.collectionsType == collectionsType) {
                val mutableLiveData = MutableLiveData<StatModel>().apply {
                    value = StatModel(context, operationTypes) { v -> value = v }
                }
                models.add(mutableLiveData)
            }
        }
        return models
    }
}