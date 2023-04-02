package com.magdenbt.collectionsbenchmark.di.fragments

import com.magdenbt.collectionsbenchmark.CollectionsType
import com.magdenbt.collectionsbenchmark.ui.viewflow.ViewPagerFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ViewPagerFragmentModule::class])
interface ViewPagerFragmentComponent {

    @Subcomponent.Builder
    interface Builder{
        fun collectionType(@BindsInstance collectionsType: CollectionsType): Builder
        fun VPFragment(@BindsInstance viewPagerFragment: ViewPagerFragment): Builder
        fun build():ViewPagerFragmentComponent
    }
    fun inject(viewPagerFragment: ViewPagerFragment)
}