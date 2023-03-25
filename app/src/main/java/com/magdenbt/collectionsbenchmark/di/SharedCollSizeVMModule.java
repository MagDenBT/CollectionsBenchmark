package com.magdenbt.collectionsbenchmark.di;

import androidx.lifecycle.ViewModelProvider;

import com.magdenbt.collectionsbenchmark.UI.ViewFlow.MainActivity;
import com.magdenbt.collectionsbenchmark.UI.SharedCollSizeVM;
import com.magdenbt.collectionsbenchmark.UI.ViewFlow.VPFragment;
import com.magdenbt.collectionsbenchmark.di.Qualifiers.MainActivityQ;
import com.magdenbt.collectionsbenchmark.di.Qualifiers.VPFragmentQ;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedCollSizeVMModule {

    @MainActivityQ
    @Provides
    public static SharedCollSizeVM provideSharedCollSizeVM(MainActivity activity){
        return new ViewModelProvider(activity).get(SharedCollSizeVM .class);

    }

    @VPFragmentQ
    @Provides
    public static SharedCollSizeVM providerSharedCollSizeVM(VPFragment fragment){
        return new ViewModelProvider(fragment.getActivity()).get(SharedCollSizeVM .class);

    }
}
