package com.magdenbt.collectionsbenchmark.ui.viewmodelflow

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class StatViewModelFactory(
    val context: Context,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatViewModel::class.java)) {
            return StatViewModel(context = context) as T
        }
        Log.e(this.javaClass.canonicalName, "${modelClass.canonicalName} - unknown ViewModel class")
        throw IllegalAccessException("Unknown ViewModel class")
    }
}
