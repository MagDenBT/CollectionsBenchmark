package com.magdenbt.collectionsbenchmark.UI.Stat;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DiffUtil;

import com.magdenbt.collectionsbenchmark.DataModel;

public class DataDiffCallback extends DiffUtil.ItemCallback<MutableLiveData<DataModel>> {

    @Override
    public boolean areItemsTheSame(@NonNull MutableLiveData<DataModel> oldItem, @NonNull MutableLiveData<DataModel> newItem) {
        return oldItem.getValue().equals(newItem.getValue());
    }

    @Override
    public boolean areContentsTheSame(@NonNull  MutableLiveData<DataModel> oldItem, @NonNull  MutableLiveData<DataModel> newItem) {
        return oldItem.getValue().getBusy() == newItem.getValue().getBusy() && oldItem.getValue().getStatus().equals(newItem.getValue().getStatus()) ;
    }

}
