package com.magdenbt.collectionsbenchmark.ui.viewmodelflow

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import com.magdenbt.collectionsbenchmark.modelflow.StatModel

class StatDiffCallback : DiffUtil.ItemCallback<LiveData<StatModel>>() {

    override fun areItemsTheSame(
        oldItem: LiveData<StatModel>, newItem: LiveData<StatModel>
    ) = oldItem.value == newItem.value

    override fun areContentsTheSame(
        oldItem: LiveData<StatModel>, newItem: LiveData<StatModel>
    ) =
        oldItem.value!!.busy == newItem.value!!.busy && oldItem.value!!.status == newItem.value!!.status
}