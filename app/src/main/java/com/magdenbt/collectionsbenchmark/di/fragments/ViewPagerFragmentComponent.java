package com.magdenbt.collectionsbenchmark.di.fragments;

import com.magdenbt.collectionsbenchmark.CollectionsType;
import com.magdenbt.collectionsbenchmark.ui.viewflow.ViewPagerFragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent(modules = {ViewPagerFragmentModule.class})
public interface ViewPagerFragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder collectionType(@BindsInstance CollectionsType collectionsType);

        Builder VPFragment(@BindsInstance ViewPagerFragment fragment);

        ViewPagerFragmentComponent build();
    }


    void inject(ViewPagerFragment vpFragment);

}
