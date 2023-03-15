package com.magdenbt.collectionsbenchmark.UI.main;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.magdenbt.collectionsbenchmark.CollectionsType;

public class VPAdapter extends FragmentStateAdapter{

    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        CollectionsType collectionsType;
        if(position == 0){
            collectionsType = CollectionsType.LIST;
        }else {
            collectionsType = CollectionsType.MAP;
        }
        return new VPFragment(collectionsType);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
