package com.magdenbt.collectionsbenchmark.ui.viewmodelflow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.magdenbt.collectionsbenchmark.CollectionsType
import com.magdenbt.collectionsbenchmark.modelflow.StatModel
import com.magdenbt.collectionsbenchmark.modelflow.StatRepository

class StatViewModel(
    _application: Application,
    private val statRepository: StatRepository,
    private val collectionsType: CollectionsType
) : AndroidViewModel(_application) {

    val statModelsLD: List<LiveData<StatModel>> by lazy {
        statRepository.getModels(collectionsType)
    }

    fun startBenchmark(sizeCollection: Int, amountElements: Int) {
        for (liveData in statModelsLD) {
            liveData.value?.startBenchmark(sizeCollection, amountElements)
        }
    }
}