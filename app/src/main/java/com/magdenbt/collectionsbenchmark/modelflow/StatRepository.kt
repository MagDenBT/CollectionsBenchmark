package com.magdenbt.collectionsbenchmark.modelflow

import android.content.Context
import com.magdenbt.collectionsbenchmark.CollectionsType
import javax.inject.Inject

class StatRepository @Inject constructor(private val context: Context) {

    fun getModels(collectionsType: CollectionsType): List<StatModel> {
        val models = mutableListOf<StatModel>()
        for (operationTypes in OperationTypes.values()) {
            if (operationTypes.collectionsType == collectionsType) {
                models.add(StatModel(context, operationTypes))
            }
        }
        return models
    }
}
