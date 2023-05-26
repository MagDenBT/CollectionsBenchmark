package com.magdenbt.collectionsbenchmark.di

import androidx.lifecycle.ViewModelProvider
import com.magdenbt.collectionsbenchmark.di.qualifiers.MainActivityQ
import com.magdenbt.collectionsbenchmark.ui.SharedCollSizeViewModel
import com.magdenbt.collectionsbenchmark.ui.viewflow.MainActivity
import dagger.Module
import dagger.Provides

@Module
class SharedCollSizeViewModelModule {

    companion object {
        @MainActivityQ
        @Provides
        fun provideSharedCollSizeVM(mainActivity: MainActivity): SharedCollSizeViewModel {
            return ViewModelProvider(mainActivity).get(SharedCollSizeViewModel::class.java)
        }

//        @ViewPagerFragmentQ
//        @Provides
//        fun providerSharedCollSizeVM( fragment: ViewPagerFragment): SharedCollSizeViewModel{
//            return ViewModelProvider(fragment).get(SharedCollSizeViewModel::class.java)
//        }
    }
}
