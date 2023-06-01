package com.magdenbt.collectionsbenchmark.di.mainactivity

import com.magdenbt.collectionsbenchmark.ui.mainactivity.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance mainActivity: MainActivity): MainComponent
    }

    fun inject(mainActivity: MainActivity)
}
