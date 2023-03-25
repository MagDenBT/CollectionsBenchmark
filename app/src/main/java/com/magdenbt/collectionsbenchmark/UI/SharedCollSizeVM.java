package com.magdenbt.collectionsbenchmark.UI;

import androidx.lifecycle.ViewModel;

import java.util.Objects;

public class SharedCollSizeVM extends ViewModel {
    private int collectionsSize = 0;

    public void setCollectionsSize(int collectionsSize){
        this.collectionsSize = collectionsSize;
    }

    public int getCollectionSize(){
     return collectionsSize;
    }
}
