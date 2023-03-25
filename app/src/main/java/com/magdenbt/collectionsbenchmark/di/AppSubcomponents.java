package com.magdenbt.collectionsbenchmark.di;

import com.magdenbt.collectionsbenchmark.di.Fragments.VPFragmentComponent;
import com.magdenbt.collectionsbenchmark.di.MainActivity.MainComponent;

import dagger.Module;


@Module(subcomponents = {MainComponent.class, VPFragmentComponent.class})
class AppSubcomponents {
}
