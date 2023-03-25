package com.magdenbt.collectionsbenchmark.UI.ViewModelFlow;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.magdenbt.collectionsbenchmark.R;
import com.magdenbt.collectionsbenchmark.modelflow.StatModel;
import com.magdenbt.collectionsbenchmark.databinding.StatItemBinding;

import javax.inject.Inject;


public class StatAdapter extends ListAdapter<LiveData<StatModel>, StatAdapter.ViewHolder>{


    @Inject
    public StatAdapter(@NonNull DiffUtil.ItemCallback<LiveData<StatModel>> itemCallback) {
        super(itemCallback);
    }

    @NonNull
    @Override
    public StatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        StatItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.stat_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(StatAdapter.ViewHolder holder, int position) {
       holder.bind(getItem(position));
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        StatItemBinding binding;

        ViewHolder(StatItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(LiveData<StatModel> ldStatisticModel){
            binding.setLdStatModel(ldStatisticModel);
        }
    }
}
