package com.magdenbt.collectionsbenchmark.di

import androidx.lifecycle.ViewModelProvider
import com.magdenbt.collectionsbenchmark.ui.mainactivity.MainActivity
import com.magdenbt.collectionsbenchmark.viewmodelflow.SharedCollSizeViewModel
import dagger.Module
import dagger.Provides

@Module
class SharedCollSizeViewModelModule {

    companion object {
        @Provides
        fun provideSharedCollSizeVM(mainActivity: MainActivity): SharedCollSizeViewModel {
            return ViewModelProvider(mainActivity).get(SharedCollSizeViewModel::class.java)
        }
    }
}
