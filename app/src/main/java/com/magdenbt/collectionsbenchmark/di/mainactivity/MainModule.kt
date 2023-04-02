package com.magdenbt.collectionsbenchmark.di.mainactivity

import androidx.fragment.app.FragmentActivity
import com.magdenbt.collectionsbenchmark.di.SharedCollSizeViewModelModule
import com.magdenbt.collectionsbenchmark.ui.viewflow.MainActivity
import dagger.Binds
import dagger.Module

@Module(includes = [SharedCollSizeViewModelModule::class])
abstract class MainModule {
    @Binds
    abstract fun bindFragmentActivity(mainActivity: MainActivity):FragmentActivity
}