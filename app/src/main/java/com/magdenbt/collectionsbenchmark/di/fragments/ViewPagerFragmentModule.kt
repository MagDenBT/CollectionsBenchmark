// package com.magdenbt.collectionsbenchmark.di.fragments
//
// import android.app.Application
// import androidx.lifecycle.ViewModelProvider
// import androidx.lifecycle.ViewModelStoreOwner
// import com.magdenbt.collectionsbenchmark.CollectionsType
// import com.magdenbt.collectionsbenchmark.di.SharedCollSizeViewModelModule
// import com.magdenbt.collectionsbenchmark.modelflow.StatRepository
// import com.magdenbt.collectionsbenchmark.ui.viewflow.ViewPagerFragment
// import com.magdenbt.collectionsbenchmark.ui.viewmodelflow.StatViewModelFactory
// import dagger.Binds
// import dagger.Module
// import dagger.Provides
//
// @Module(includes = [SharedCollSizeViewModelModule::class])
// abstract class ViewPagerFragmentModule {
//
//    @Binds
//    abstract fun bindViewModelStoreOwner(viewPagerFragment: ViewPagerFragment): ViewModelStoreOwner
//
//    companion object {
//        @Provides
//        fun provideViewModelProvider(
//            viewModelStoreOwner: ViewModelStoreOwner,
//            statRepository: StatRepository,
//            application: Application,
//            collectionsType: CollectionsType,
//        ): ViewModelProvider {
//            return ViewModelProvider(
//                viewModelStoreOwner,
//                StatViewModelFactory(statRepository, collectionsType),
//            )
//        }
//    }
// }
