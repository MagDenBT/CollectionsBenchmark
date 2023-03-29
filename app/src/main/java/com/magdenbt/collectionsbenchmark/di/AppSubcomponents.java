package com.magdenbt.collectionsbenchmark.di;

import com.magdenbt.collectionsbenchmark.di.fragments.ViewPagerFragmentComponent;
import com.magdenbt.collectionsbenchmark.di.mainactivity.MainComponent;

import dagger.Module;


@Module(subcomponents = {MainComponent.class, ViewPagerFragmentComponent.class})
class AppSubcomponents {
}
