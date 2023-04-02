package com.magdenbt.collectionsbenchmark.di;

import androidx.lifecycle.ViewModelProvider;

import com.magdenbt.collectionsbenchmark.di.qualifiers.ViewPagerFragmentQ;
import com.magdenbt.collectionsbenchmark.ui.SharedCollSizeViewModel;
import com.magdenbt.collectionsbenchmark.ui.viewflow.MainActivity;
import com.magdenbt.collectionsbenchmark.ui.viewflow.ViewPagerFragment;
import com.magdenbt.collectionsbenchmark.di.qualifiers.MainActivityQ;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedCollSizeViewModelModule {

    @MainActivityQ
    @Provides
    public static SharedCollSizeViewModel provideSharedCollSizeVM(MainActivity activity) {
        return new ViewModelProvider(activity).get(SharedCollSizeViewModel.class);

    }

    @ViewPagerFragmentQ
    @Provides
    public static SharedCollSizeViewModel providerSharedCollSizeVM(ViewPagerFragment fragment) {
        return new ViewModelProvider(fragment.getActivity()).get(SharedCollSizeViewModel.class);

    }
}
