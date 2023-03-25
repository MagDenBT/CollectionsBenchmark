package com.magdenbt.collectionsbenchmark.di.Fragments;

import com.magdenbt.collectionsbenchmark.CollectionsType;
import com.magdenbt.collectionsbenchmark.UI.ViewFlow.VPFragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent(modules = {VPFragmentModule.class})
public interface VPFragmentComponent {

    @Subcomponent.Builder
    interface Builder{
        Builder collectionType(@BindsInstance CollectionsType collectionsType);
        Builder VPFragment(@BindsInstance VPFragment fragment);

        VPFragmentComponent build();
    }


    void inject(VPFragment vpFragment);

}
