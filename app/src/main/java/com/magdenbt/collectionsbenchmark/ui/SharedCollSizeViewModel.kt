package com.magdenbt.collectionsbenchmark.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedCollSizeViewModel : ViewModel() {
    val collectionSize = mutableStateOf(0)
}
