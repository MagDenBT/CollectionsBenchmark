package com.magdenbt.collectionsbenchmark;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.Observer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StatModel implements SingleObserver<String>  {


    private final OperationTypes operationType;
    private final Observer<StatModel> observer;
    private final Context context;
    private int busy = View.GONE;
    private String prefix;
    private String collectionType;
    private String duration = "N/A";




    public StatModel(Context context, OperationTypes operationType, Observer<StatModel> observer) {
        this.context = context;
        this.operationType = operationType;
        this.observer = observer;

        setTexts();
    }



    private void setTexts() {
        duration = "N/A";

        switch (operationType) {

            case ARRAY_LIST_ADDING_IN_THE_BEGINNING:
                prefix = context.getString(R.string.operation_status_adding_beginning);
                collectionType = ArrayList.class.getSimpleName();
                break;
            case ARRAY_LIST_ADDING_IN_THE_MIDDLE:
                prefix = context.getString(R.string.operation_status_adding_middle);
                collectionType = ArrayList.class.getSimpleName();
                break;
            case ARRAY_LIST_ADDING_IN_THE_END:
                prefix = context.getString(R.string.operation_status_adding_end);
                collectionType = ArrayList.class.getSimpleName();
                break;
            case ARRAY_LIST_SEARCH_BY_VALUE:
                prefix = context.getString(R.string.operation_status_search_by_value);
                collectionType = ArrayList.class.getSimpleName();
                break;
            case ARRAY_LIST_REMOVING_IN_THE_BEGINNING:
                prefix = context.getString(R.string.operation_status_removing_beginning);
                collectionType = ArrayList.class.getSimpleName();
                break;
            case ARRAY_LIST_REMOVING_IN_THE_MIDDLE:
                prefix = context.getString(R.string.operation_status_removing_middle);
                collectionType = ArrayList.class.getSimpleName();
                break;
            case ARRAY_LIST_REMOVING_IN_THE_END:
                prefix = context.getString(R.string.operation_status_removing_end);
                collectionType = ArrayList.class.getSimpleName();
                break;

            case LINKED_LIST_ADDING_IN_THE_BEGINNING:
                prefix = context.getString(R.string.operation_status_adding_beginning);
                collectionType = LinkedList.class.getSimpleName();
                break;
            case LINKED_LIST_ADDING_IN_THE_MIDDLE:
                prefix = context.getString(R.string.operation_status_adding_middle);
                collectionType = LinkedList.class.getSimpleName();
                break;
            case LINKED_LIST_ADDING_IN_THE_END:
                prefix = context.getString(R.string.operation_status_adding_end);
                collectionType = LinkedList.class.getSimpleName();
                break;
            case LINKED_LIST_SEARCH_BY_VALUE:
                prefix = context.getString(R.string.operation_status_search_by_value);
                collectionType = LinkedList.class.getSimpleName();
                break;
            case LINKED_LIST_REMOVING_IN_THE_BEGINNING:
                prefix = context.getString(R.string.operation_status_removing_beginning);
                collectionType = LinkedList.class.getSimpleName();
                break;
            case LINKED_LIST_REMOVING_IN_THE_MIDDLE:
                prefix = context.getString(R.string.operation_status_removing_middle);
                collectionType = LinkedList.class.getSimpleName();
                break;
            case LINKED_LIST_REMOVING_IN_THE_END:
                prefix = context.getString(R.string.operation_status_removing_end);
                collectionType = LinkedList.class.getSimpleName();
                break;

            case COPY_ON_WRITE_AL_ADDING_IN_THE_BEGINNING:
                prefix = context.getString(R.string.operation_status_adding_beginning);
                collectionType = CopyOnWriteArrayList.class.getSimpleName();
                break;
            case COPY_ON_WRITE_AL_ADDING_IN_THE_MIDDLE:
                prefix = context.getString(R.string.operation_status_adding_middle);
                collectionType = CopyOnWriteArrayList.class.getSimpleName();
                break;
            case COPY_ON_WRITE_AL_ADDING_IN_THE_END:
                prefix = context.getString(R.string.operation_status_adding_end);
                collectionType = CopyOnWriteArrayList.class.getSimpleName();
                break;
            case COPY_ON_WRITE_AL_SEARCH_BY_VALUE:
                prefix = context.getString(R.string.operation_status_search_by_value);
                collectionType = CopyOnWriteArrayList.class.getSimpleName();
                break;
            case COPY_ON_WRITE_AL_REMOVING_IN_THE_BEGINNING:
                prefix = context.getString(R.string.operation_status_removing_beginning);
                collectionType = CopyOnWriteArrayList.class.getSimpleName();
                break;
            case COPY_ON_WRITE_AL_REMOVING_IN_THE_MIDDLE:
                prefix = context.getString(R.string.operation_status_removing_middle);
                collectionType = CopyOnWriteArrayList.class.getSimpleName();
                break;
            case COPY_ON_WRITE_AL_REMOVING_IN_THE_END:
                prefix = context.getString(R.string.operation_status_removing_end);
                collectionType = CopyOnWriteArrayList.class.getSimpleName();
                break;

            case HASH_MAP_ADDING_NEW:
                prefix = context.getString(R.string.operation_status_adding_new);
                collectionType = HashMap.class.getSimpleName();
                break;
            case HASH_MAP_SEARCH_BY_KEY:
                prefix = context.getString(R.string.operation_status_search_by_key);
                collectionType = HashMap.class.getSimpleName();
                break;
            case HASH_MAP_REMOVING:
                prefix = context.getString(R.string.operation_status_removing);
                collectionType = HashMap.class.getSimpleName();
                break;

            case TREE_MAP_ADDING_NEW:
                prefix = context.getString(R.string.operation_status_adding_new);
                collectionType = TreeMap.class.getSimpleName();
                break;
            case TREE_MAP_SEARCH_BY_KEY:
                prefix = context.getString(R.string.operation_status_search_by_key);
                collectionType = TreeMap.class.getSimpleName();
                break;
            case TREE_MAP_REMOVING:
                prefix = context.getString(R.string.operation_status_removing);
                collectionType = TreeMap.class.getSimpleName();
                break;
            default:
                prefix = "UNKNOWN OPERATION";
                collectionType = "ERROR";
        }
    }

    public int getBusy() {
        return busy;
    }

    public String getStatus() {
        return prefix + " " + collectionType + " " + duration + " ms";
    }


    public void startBenchmark(int sizeCollection, int amountElements){
        OperationBenchmark.createOb(sizeCollection, amountElements, operationType).
                observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.single()).
                map(String::valueOf).
                subscribe(this);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        busy = View.VISIBLE;
        observer.onChanged(this);
    }

    @Override
    public void onSuccess(@NonNull String duration) {
        this.duration = duration;
        busy = View.GONE;
        observer.onChanged(this);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        duration = "ERROR";
        busy = View.GONE;
        observer.onChanged(this);
    }
}
