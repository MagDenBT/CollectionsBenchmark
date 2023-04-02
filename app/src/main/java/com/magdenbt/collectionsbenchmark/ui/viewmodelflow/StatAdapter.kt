package com.magdenbt.collectionsbenchmark.ui.viewmodelflow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.magdenbt.collectionsbenchmark.R
import com.magdenbt.collectionsbenchmark.databinding.StatItemBinding
import com.magdenbt.collectionsbenchmark.modelflow.StatModel

class StatAdapter(itemCallback: DiffUtil.ItemCallback<LiveData<StatModel>>) :
    ListAdapter<LiveData<StatModel>, StatAdapter.ViewHolder>(itemCallback) {

    class ViewHolder(val binding: StatItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(ldStatisticModel: LiveData<StatModel>) {
            binding.ldStatModel = ldStatisticModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: StatItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.stat_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}