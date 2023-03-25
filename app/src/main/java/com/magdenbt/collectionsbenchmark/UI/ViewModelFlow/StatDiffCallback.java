package com.magdenbt.collectionsbenchmark.UI.ViewModelFlow;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;

import com.magdenbt.collectionsbenchmark.modelflow.StatModel;

import javax.inject.Inject;

public class StatDiffCallback extends DiffUtil.ItemCallback<LiveData<StatModel>> {

    @Inject
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
