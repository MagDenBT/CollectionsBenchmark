package com.magdenbt.collectionsbenchmark.modelflow

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.magdenbt.collectionsbenchmark.OperationBenchmark
import com.magdenbt.collectionsbenchmark.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

class StatModel(
    val context: Context, val operationType: OperationTypes, val observer: Observer<StatModel>
) : SingleObserver<String> {

    private var prefix = ""
    private var collectionType = ""
    private var duration = "N/A"

    init {
        setTexts()
    }



    var busy = View.GONE
        private set

    val status
        get() = "$prefix $collectionType $duration ms"

    private fun setTexts() {
        when (operationType) {
            OperationTypes.ARRAY_LIST_ADDING_IN_THE_BEGINNING -> {
                prefix = context.getString(R.string.operation_status_adding_beginning)
                collectionType = ArrayList::class.java.simpleName
            }
            OperationTypes.ARRAY_LIST_ADDING_IN_THE_MIDDLE -> {
                prefix = context.getString(R.string.operation_status_adding_middle)
                collectionType = ArrayList::class.java.simpleName
            }
            OperationTypes.ARRAY_LIST_ADDING_IN_THE_END -> {
                prefix = context.getString(R.string.operation_status_adding_end)
                collectionType = ArrayList::class.java.simpleName
            }
            OperationTypes.ARRAY_LIST_SEARCH_BY_VALUE -> {
                prefix = context.getString(R.string.operation_status_search_by_value)
                collectionType = ArrayList::class.java.simpleName
            }
            OperationTypes.ARRAY_LIST_REMOVING_IN_THE_BEGINNING -> {
                prefix = context.getString(R.string.operation_status_removing_beginning)
                collectionType = ArrayList::class.java.simpleName
            }
            OperationTypes.ARRAY_LIST_REMOVING_IN_THE_MIDDLE -> {
                prefix = context.getString(R.string.operation_status_removing_middle)
                collectionType = ArrayList::class.java.simpleName
            }
            OperationTypes.ARRAY_LIST_REMOVING_IN_THE_END -> {
                prefix = context.getString(R.string.operation_status_removing_end)
                collectionType = ArrayList::class.java.simpleName
            }
            OperationTypes.LINKED_LIST_ADDING_IN_THE_BEGINNING -> {
                prefix = context.getString(R.string.operation_status_adding_beginning)
                collectionType = LinkedList::class.java.simpleName
            }
            OperationTypes.LINKED_LIST_ADDING_IN_THE_MIDDLE -> {
                prefix = context.getString(R.string.operation_status_adding_middle)
                collectionType = LinkedList::class.java.simpleName
            }
            OperationTypes.LINKED_LIST_ADDING_IN_THE_END -> {
                prefix = context.getString(R.string.operation_status_adding_end)
                collectionType = LinkedList::class.java.simpleName
            }
            OperationTypes.LINKED_LIST_SEARCH_BY_VALUE -> {
                prefix = context.getString(R.string.operation_status_search_by_value)
                collectionType = LinkedList::class.java.simpleName
            }
            OperationTypes.LINKED_LIST_REMOVING_IN_THE_BEGINNING -> {
                prefix = context.getString(R.string.operation_status_removing_beginning)
                collectionType = LinkedList::class.java.simpleName
            }
            OperationTypes.LINKED_LIST_REMOVING_IN_THE_MIDDLE -> {
                prefix = context.getString(R.string.operation_status_removing_middle)
                collectionType = LinkedList::class.java.simpleName
            }
            OperationTypes.LINKED_LIST_REMOVING_IN_THE_END -> {
                prefix = context.getString(R.string.operation_status_removing_end)
                collectionType = LinkedList::class.java.simpleName
            }
            OperationTypes.COPY_ON_WRITE_AL_ADDING_IN_THE_BEGINNING -> {
                prefix = context.getString(R.string.operation_status_adding_beginning)
                collectionType = CopyOnWriteArrayList::class.java.simpleName
            }
            OperationTypes.COPY_ON_WRITE_AL_ADDING_IN_THE_MIDDLE -> {
                prefix = context.getString(R.string.operation_status_adding_middle)
                collectionType = CopyOnWriteArrayList::class.java.simpleName
            }
            OperationTypes.COPY_ON_WRITE_AL_ADDING_IN_THE_END -> {
                prefix = context.getString(R.string.operation_status_adding_end)
                collectionType = CopyOnWriteArrayList::class.java.simpleName
            }
            OperationTypes.COPY_ON_WRITE_AL_SEARCH_BY_VALUE -> {
                prefix = context.getString(R.string.operation_status_search_by_value)
                collectionType = CopyOnWriteArrayList::class.java.simpleName
            }
            OperationTypes.COPY_ON_WRITE_AL_REMOVING_IN_THE_BEGINNING -> {
                prefix = context.getString(R.string.operation_status_removing_beginning)
                collectionType = CopyOnWriteArrayList::class.java.simpleName
            }
            OperationTypes.COPY_ON_WRITE_AL_REMOVING_IN_THE_MIDDLE -> {
                prefix = context.getString(R.string.operation_status_removing_middle)
                collectionType = CopyOnWriteArrayList::class.java.simpleName
            }
            OperationTypes.COPY_ON_WRITE_AL_REMOVING_IN_THE_END -> {
                prefix = context.getString(R.string.operation_status_removing_end)
                collectionType = CopyOnWriteArrayList::class.java.simpleName
            }
            OperationTypes.HASH_MAP_ADDING_NEW -> {
                prefix = context.getString(R.string.operation_status_adding_new)
                collectionType = HashMap::class.java.simpleName
            }
            OperationTypes.HASH_MAP_SEARCH_BY_KEY -> {
                prefix = context.getString(R.string.operation_status_search_by_key)
                collectionType = HashMap::class.java.simpleName
            }
            OperationTypes.HASH_MAP_REMOVING -> {
                prefix = context.getString(R.string.operation_status_removing)
                collectionType = HashMap::class.java.simpleName
            }
            OperationTypes.TREE_MAP_ADDING_NEW -> {
                prefix = context.getString(R.string.operation_status_adding_new)
                collectionType = TreeMap::class.java.simpleName
            }
            OperationTypes.TREE_MAP_SEARCH_BY_KEY -> {
                prefix = context.getString(R.string.operation_status_search_by_key)
                collectionType = TreeMap::class.java.simpleName
            }
            OperationTypes.TREE_MAP_REMOVING -> {
                prefix = context.getString(R.string.operation_status_removing)
                collectionType = TreeMap::class.java.simpleName
            }
            else -> {
                prefix = "UNKNOWN OPERATION"
                collectionType = "ERROR"
            }
        }
    }

    fun startBenchmark(sizeCollection: Int, amountElements: Int) {
        OperationBenchmark.createOb(sizeCollection, amountElements, operationType)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.single())
            .map { "$it" }
            .subscribe(this)
    }

    override fun onSubscribe(d: Disposable) {
        busy = View.VISIBLE
        observer.onChanged(this)
    }

    override fun onSuccess(_duration: String) {
        duration = _duration
        busy = View.GONE
        observer.onChanged(this)
    }

    override fun onError(e: Throwable) {
        duration = "ERROR"
        busy = View.GONE
        Log.e(this.javaClass.canonicalName, "Duration error - ${e.message}")
        observer.onChanged(this)
    }
}