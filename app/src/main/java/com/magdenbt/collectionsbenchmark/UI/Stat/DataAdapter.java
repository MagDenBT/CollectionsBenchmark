package com.magdenbt.collectionsbenchmark.UI.Stat;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.magdenbt.collectionsbenchmark.R;
import com.magdenbt.collectionsbenchmark.DataModel;
import com.magdenbt.collectionsbenchmark.databinding.DataItemBinding;


public class DataAdapter extends ListAdapter<MutableLiveData<DataModel>, DataAdapter.ViewHolder>{


    public DataAdapter(@NonNull DiffUtil.ItemCallback<MutableLiveData<DataModel>> itemCallback) {
        super(itemCallback);
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DataItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.data_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
       holder.bind(getItem(position));
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        DataItemBinding binding;

        ViewHolder(DataItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(MutableLiveData<DataModel> ldStatisticModel){
            binding.setLdStatModel(ldStatisticModel);
        }
    }
}
