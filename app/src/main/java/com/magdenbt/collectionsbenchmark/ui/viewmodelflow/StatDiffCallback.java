package com.magdenbt.collectionsbenchmark.ui.viewmodelflow;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;

import com.magdenbt.collectionsbenchmark.modelflow.StatModel;


public class StatDiffCallback extends DiffUtil.ItemCallback<LiveData<StatModel>> {

    public StatDiffCallback() {
    }

    @Override
    public boolean areItemsTheSame(@NonNull LiveData<StatModel> oldItem, @NonNull LiveData<StatModel> newItem) {
        return oldItem.getValue().equals(newItem.getValue());
    }

    @Override
    public boolean areContentsTheSame(@NonNull  LiveData<StatModel> oldItem, @NonNull  LiveData<StatModel> newItem) {
        return oldItem.getValue().getBusy() == newItem.getValue().getBusy() && oldItem.getValue().getStatus().equals(newItem.getValue().getStatus()) ;
    }

}
