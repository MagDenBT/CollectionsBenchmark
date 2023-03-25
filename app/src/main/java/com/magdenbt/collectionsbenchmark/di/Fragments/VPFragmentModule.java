package com.magdenbt.collectionsbenchmark.di.Fragments;

import android.app.Application;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.magdenbt.collectionsbenchmark.CollectionsType;
import com.magdenbt.collectionsbenchmark.modelflow.StatModel;
import com.magdenbt.collectionsbenchmark.UI.ViewModelFlow.StatDiffCallback;
import com.magdenbt.collectionsbenchmark.UI.ViewModelFlow.StatVMFactory;
import com.magdenbt.collectionsbenchmark.UI.ViewFlow.VPFragment;
import com.magdenbt.collectionsbenchmark.di.SharedCollSizeVMModule;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = SharedCollSizeVMModule.class)
public abstract class VPFragmentModule {

//    @Provides
//    public static VPFragment provideVPFragment(CollectionsType collectionsType){
//        return new VPFragment(collectionsType);
//    }

    @Binds
    public abstract ViewModelStoreOwner bindViewModelStoreOwner(VPFragment vpFragment);

    @Binds
    public abstract DiffUtil.ItemCallback<LiveData<StatModel>> bindStatDiffCallback(StatDiffCallback statDiffCallback);


    @Provides
    public static ViewModelProvider provideViewModelProvider(ViewModelStoreOwner viewModelStoreOwner, Application application, CollectionsType collectionsType){
        return new ViewModelProvider(viewModelStoreOwner, new StatVMFactory(application, collectionsType));
    }

    @Provides
    public static GridLayoutManager provideGridLayoutManager(Context context, CollectionsType collectionsType){
        return new GridLayoutManager(context, collectionsType == CollectionsType.LIST ? 3 : 2);
    }

    @Provides
    public static Animation probideShake(){
        Interpolator cycleInterpolator = new CycleInterpolator(7);
        Animation shake = new TranslateAnimation(-10, 10, 0, 0);
        shake.setDuration(50);
        shake.setInterpolator(cycleInterpolator);
        return shake;
    }


}
