package com.magdenbt.collectionsbenchmark.di.mainactivity

import com.magdenbt.collectionsbenchmark.di.SharedCollSizeViewModelModule
import dagger.Module

@Module(includes = [SharedCollSizeViewModelModule::class])
abstract class MainModule {
//    @Binds
//    abstract fun bindFragmentActivity(mainActivity: MainActivity):FragmentActivity
}
