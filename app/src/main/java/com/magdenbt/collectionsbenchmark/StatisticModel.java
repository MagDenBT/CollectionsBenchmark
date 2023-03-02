package com.magdenbt.collectionsbenchmark;

import android.content.Context;

import androidx.lifecycle.Observer;

public class StatisticModel implements OperationListener {


    private final OperationTypes operationType;
    private final Class collectionType;
    private final Observer<StatisticModel> observer;
    private final Context context;
    private String prefix;
    private String duration;


    public StatisticModel(Context context, OperationTypes operationType, Class collectionType, Observer<StatisticModel> observer) {
        this.observer = observer;
        this.context = context;
        this.operationType = operationType;
        this.collectionType = collectionType;
        this.duration = "N/A";
        setPrefix();


    }

    private void setPrefix() {
        switch (operationType) {
            case ADDINGINTHEBEGINNING:
                prefix = context.getString(R.string.operation_status_adding_beginning);
                break;
            case ADDINGINTHEMIDDLE:
                prefix = context.getString(R.string.operation_status_adding_middle);
                break;
            case ADDINGINTHEEND:
                prefix = context.getString(R.string.operation_status_adding_end);
                break;
            case SEARCHBYVALUE:
                prefix = context.getString(R.string.operation_status_search_by_value);
                break;
            case REMOVINGINTHEBEGINNING:
                prefix = context.getString(R.string.operation_status_removing_beginning);
                break;
            case REMOVINGINTHEMIDDLE:
                prefix = context.getString(R.string.operation_status_removing_middle);
                break;
            case REMOVINGINTHEEND:
                prefix = context.getString(R.string.operation_status_removing_end);
                break;
            case ADDINGNEW:
                prefix = context.getString(R.string.operation_status_adding_new);
                break;
            case SEARCHBYKEY:
                prefix = context.getString(R.string.operation_status_search_by_key);
                break;
            case REMOVING:
                prefix = context.getString(R.string.operation_status_removing);
                break;
            default:
                prefix = "UNKNOWN OPERATION";
        }
    }

    public String getStatus() {

        return prefix + " " + collectionType.getSimpleName() + " " + duration + " ms";
    }

    @Override
    public void dataOperationComplete(Class collectionType, OperationTypes operationType, long duration) {
        if (this.collectionType == collectionType && this.operationType == operationType) {
            this.duration = String.valueOf(duration);
            observer.onChanged(this);
        }
    }



}
