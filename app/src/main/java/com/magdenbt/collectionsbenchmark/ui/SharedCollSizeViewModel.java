package com.magdenbt.collectionsbenchmark.ui;

import androidx.lifecycle.ViewModel;

public class SharedCollSizeViewModel extends ViewModel {
    private int collectionsSize = 0;

    public void setCollectionsSize(int collectionsSize){
        this.collectionsSize = collectionsSize;
    }

    public int getCollectionSize(){
     return collectionsSize;
    }
}
