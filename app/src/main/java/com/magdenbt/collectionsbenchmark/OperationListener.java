package com.magdenbt.collectionsbenchmark;

public interface OperationListener {

    void dataOperationComplete(Class collectionType, OperationTypes operationType, long duration);

}
