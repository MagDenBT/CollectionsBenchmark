package com.magdenbt.collectionsbenchmark.UI.Stat;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DiffUtil;

import com.magdenbt.collectionsbenchmark.StatModel;

public class StatDiffCallback extends DiffUtil.ItemCallback<MutableLiveData<StatModel>> {

    @Override
    public boolean areItemsTheSame(@NonNull MutableLiveData<StatModel> oldItem, @NonNull MutableLiveData<StatModel> newItem) {
        return oldItem.getValue().equals(newItem.getValue());
    }

    @Override
    public boolean areContentsTheSame(@NonNull  MutableLiveData<StatModel> oldItem, @NonNull  MutableLiveData<StatModel> newItem) {
        return oldItem.getValue().getBusy() == newItem.getValue().getBusy() && oldItem.getValue().getStatus().equals(newItem.getValue().getStatus()) ;
    }

}
