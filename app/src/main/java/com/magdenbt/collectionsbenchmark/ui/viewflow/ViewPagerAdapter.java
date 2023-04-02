package com.magdenbt.collectionsbenchmark.ui.viewflow;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.magdenbt.collectionsbenchmark.CollectionsType;

import javax.inject.Inject;

public class ViewPagerAdapter extends FragmentStateAdapter{

    @Inject
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
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
        return new ViewPagerFragment(collectionsType);
    }





    @Override
    public int getItemCount() {
        return 2;
    }

}
