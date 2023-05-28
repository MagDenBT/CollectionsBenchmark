package com.magdenbt.collectionsbenchmark.ui.viewmodelflow

import android.content.Context
import androidx.lifecycle.ViewModel
import com.magdenbt.collectionsbenchmark.modelflow.CollectionsType
import com.magdenbt.collectionsbenchmark.modelflow.StatModel
import com.magdenbt.collectionsbenchmark.modelflow.StatRepository

class StatViewModel(
    val context: Context,
) : ViewModel() {

    val statRepository: StatRepository = StatRepository(context)

    val statModelsLD: Map<CollectionsType, List<StatModel>> =
        mutableMapOf<CollectionsType, List<StatModel>>().apply {
            CollectionsType.values().forEach { collectionsType ->
                put(collectionsType, statRepository.getModels(collectionsType))
            }
        }

    fun startBenchmark(collectionsType: CollectionsType, sizeCollection: Int, amountElements: Int) {
        statModelsLD.get(collectionsType)?.iterator()
            ?.forEach { it.startBenchmark(sizeCollection, amountElements) }
    }
}
