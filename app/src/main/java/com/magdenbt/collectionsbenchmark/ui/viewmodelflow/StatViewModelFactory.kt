package com.magdenbt.collectionsbenchmark.ui.viewmodelflow

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.magdenbt.collectionsbenchmark.CollectionsType
import com.magdenbt.collectionsbenchmark.modelflow.StatRepository

class StatViewModelFactory(
    val application: Application,
    val statRepository: StatRepository,
    val collectionsType: CollectionsType
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatViewModel::class.java)) {
            return StatViewModel(application, statRepository, collectionsType) as T
        }
        Log.e(this.javaClass.canonicalName, "${modelClass.canonicalName} - unknown ViewModel class")
        throw IllegalAccessException("Unknown ViewModel class")
    }

}