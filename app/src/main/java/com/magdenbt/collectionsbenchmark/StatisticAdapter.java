package com.magdenbt.collectionsbenchmark;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.ViewHolder> implements Observer<StatisticModel>{

    private final List<StatisticModel> statisticModels;


    public StatisticAdapter(Context context, CollectionsType collectionsType) {
        this.statisticModels = StatisticModelFactory.getModels(context, collectionsType,this);
    }


    @NonNull
    @Override
    public StatisticAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.operation_item, parent, false));
    }

    @Override
    public void onBindViewHolder(StatisticAdapter.ViewHolder holder, int position) {
        holder.statusView.setText(statisticModels.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return statisticModels.size();
    }

    @Override
    public void onChanged(StatisticModel statisticModel) {
        notifyItemChanged(statisticModels.indexOf(statisticModel));
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView statusView;
        ViewHolder(View view){
            super(view);
            statusView = view.findViewById(R.id.operation);
        }
    }
}
