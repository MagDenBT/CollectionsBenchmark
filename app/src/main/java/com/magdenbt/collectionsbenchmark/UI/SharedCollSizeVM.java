package com.magdenbt.collectionsbenchmark.UI;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

public class SharedCollSizeVM extends ViewModel {
    private final MutableLiveData<Integer> liveData = new MutableLiveData<>();

    public void setCollectionsSize(int collectionsSize){
        liveData.setValue(collectionsSize);
    }

    public int getCollectionSize(){
        try {
            return Objects.requireNonNull(liveData.getValue());
        } catch (NullPointerException e){
            return 0;
        }

    }
}
