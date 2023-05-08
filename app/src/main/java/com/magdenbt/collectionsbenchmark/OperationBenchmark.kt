package com.magdenbt.collectionsbenchmark

import com.magdenbt.collectionsbenchmark.modelflow.OperationTypes
import io.reactivex.rxjava3.core.Single
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

class OperationBenchmark {

    companion object {

        fun createOb(
            sizeCollection: Int, elementsAmount: Int, operationType: OperationTypes
        ): Single<Long> {
            return Single.create { emitter ->
                emitter.onSuccess(calculateDuration(
                    sizeCollection, elementsAmount, operationType)
                )
            }
        }

        private fun calculateDuration(
            sizeCollection: Int, elementsAmount: Int, operationType: OperationTypes
        ): Long {
            return when (operationType) {
                OperationTypes.ARRAY_LIST_ADDING_IN_THE_BEGINNING -> addingBeginningArrayList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.ARRAY_LIST_ADDING_IN_THE_MIDDLE -> addingMiddleArrayList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.ARRAY_LIST_ADDING_IN_THE_END -> addingEndArrayList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.ARRAY_LIST_SEARCH_BY_VALUE -> searchByValueArrayList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.ARRAY_LIST_REMOVING_IN_THE_BEGINNING -> removingBeginningArrayList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.ARRAY_LIST_REMOVING_IN_THE_MIDDLE -> removingMiddleArrayList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.ARRAY_LIST_REMOVING_IN_THE_END -> removingEndArrayList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.LINKED_LIST_ADDING_IN_THE_BEGINNING -> addingBeginningLinkedList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.LINKED_LIST_ADDING_IN_THE_MIDDLE -> addingMiddleLinkedList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.LINKED_LIST_ADDING_IN_THE_END -> addingEndLinkedList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.LINKED_LIST_SEARCH_BY_VALUE -> searchByValueLinkedList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.LINKED_LIST_REMOVING_IN_THE_BEGINNING -> removingBeginningLinkedList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.LINKED_LIST_REMOVING_IN_THE_MIDDLE -> removingMiddleLinkedList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.LINKED_LIST_REMOVING_IN_THE_END -> removingEndLinkedList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.COPY_ON_WRITE_AL_ADDING_IN_THE_BEGINNING -> addingBeginningCopyOnWriteArrayList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.COPY_ON_WRITE_AL_ADDING_IN_THE_MIDDLE -> addingMiddleCopyOnWriteArrayList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.COPY_ON_WRITE_AL_ADDING_IN_THE_END -> addingEndCopyOnWriteArrayList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.COPY_ON_WRITE_AL_SEARCH_BY_VALUE -> searchByValueCopyOnWriteArrayList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.COPY_ON_WRITE_AL_REMOVING_IN_THE_BEGINNING -> removingBeginningCopyOnWriteArrayList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.COPY_ON_WRITE_AL_REMOVING_IN_THE_MIDDLE -> removingMiddleCopyOnWriteArrayList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.COPY_ON_WRITE_AL_REMOVING_IN_THE_END -> removingEndCopyOnWriteArrayList(
                    sizeCollection, elementsAmount
                )
                OperationTypes.HASH_MAP_ADDING_NEW -> addingHashMap(
                    sizeCollection, elementsAmount
                )
                OperationTypes.HASH_MAP_SEARCH_BY_KEY -> searchingHashMap(
                    sizeCollection, elementsAmount
                )
                OperationTypes.HASH_MAP_REMOVING -> removingHashMap(
                    sizeCollection, elementsAmount
                )
                OperationTypes.TREE_MAP_ADDING_NEW -> addingTreeMap(
                    sizeCollection, elementsAmount
                )
                OperationTypes.TREE_MAP_SEARCH_BY_KEY -> searchingTreeMap(
                    sizeCollection, elementsAmount
                )
                OperationTypes.TREE_MAP_REMOVING -> removingTreeMap(
                    sizeCollection, elementsAmount
                )
                else -> -1
            }
        }

        private fun createHashMap(sizeCollection: Int): HashMap<Int, Int> {
            val hashMap = HashMap<Int, Int>()
            for (i in 0 until sizeCollection) {
                hashMap[i] = 0
            }
            return hashMap
        }

        private fun createTreeMap(sizeCollection: Int): TreeMap<Int, Int> {
            val treeMap = TreeMap<Int, Int>()
            for (i in 0 until sizeCollection) {
                treeMap[i] = 0
            }
            return treeMap
        }

        private fun createArrayList(sizeCollection: Int): ArrayList<Int> {
            val arrayList = ArrayList<Int>()
            for (i in 0 until sizeCollection) {
                arrayList.add(0)
            }
            return arrayList
        }

        private fun createCopyOnWriteArrayList(sizeCollection: Int): CopyOnWriteArrayList<Int> {
            val copyOnWriteArrayList = CopyOnWriteArrayList<Int>()
            for (i in 0 until sizeCollection) {
                copyOnWriteArrayList.add(0)
            }
            return copyOnWriteArrayList
        }

        private fun createLinkedList(sizeCollection: Int): LinkedList<Int> {
            val linkedList = LinkedList<Int>()
            for (i in 0 until sizeCollection) {
                linkedList.add(0)
            }
            return linkedList
        }

        private fun removingEndCopyOnWriteArrayList(
            sizeCollection: Int, _elementsAmount: Int
        ): Long {
            var elementsAmount = _elementsAmount
            val ob = createCopyOnWriteArrayList(sizeCollection)
            var targetInd = sizeCollection - 1
            val start = System.currentTimeMillis()
            while (targetInd > 0 && elementsAmount > 0) {
                ob.removeAt(targetInd)
                targetInd--
                elementsAmount--
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun removingMiddleCopyOnWriteArrayList(
            _sizeCollection: Int, _elementsAmount: Int
        ): Long {
            var sizeCollection = _sizeCollection
            var elementsAmount = _elementsAmount
            val ob = createCopyOnWriteArrayList(sizeCollection)
            val start = System.currentTimeMillis()
            while (sizeCollection > 0 && elementsAmount > 0) {
                var middleInd = sizeCollection / 2 - 1
                if (middleInd < 0) middleInd = 0
                ob.removeAt(middleInd)
                sizeCollection--
                elementsAmount--
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun removingBeginningCopyOnWriteArrayList(
            _sizeCollection: Int, _elementsAmount: Int
        ): Long {
            var sizeCollection = _sizeCollection
            var elementsAmount = _elementsAmount
            val ob = createCopyOnWriteArrayList(sizeCollection)
            val start = System.currentTimeMillis()
            while (sizeCollection > 0 && elementsAmount > 0) {
                ob.removeAt(0)
                sizeCollection--
                elementsAmount--
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun searchByValueCopyOnWriteArrayList(
            sizeCollection: Int, elementsAmount: Int
        ): Long {
            val ob = createCopyOnWriteArrayList(sizeCollection)
            val value = 0
            val start = System.currentTimeMillis()
            for (i in 0 until elementsAmount) {
                ob.indexOf(value)
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun addingEndCopyOnWriteArrayList(sizeCollection: Int, elementsAmount: Int): Long {
            val ob = createCopyOnWriteArrayList(sizeCollection)
            val value = 0
            val newSize = sizeCollection + elementsAmount
            val start = System.currentTimeMillis()
            for (i in sizeCollection until newSize) {
                ob.add(i, value)
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun addingMiddleCopyOnWriteArrayList(
            sizeCollection: Int, elementsAmount: Int
        ): Long {
            val ob = createCopyOnWriteArrayList(sizeCollection)
            val value = 0
            var startInd = (sizeCollection - 1) / 2
            val endInd = startInd + elementsAmount
            val start = System.currentTimeMillis()
            while (startInd <= endInd) {
                ob.add(startInd, value)
                startInd++
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun addingBeginningCopyOnWriteArrayList(
            sizeCollection: Int, elementsAmount: Int
        ): Long {
            val ob = createCopyOnWriteArrayList(sizeCollection)
            val value = 0
            val start = System.currentTimeMillis()
            for (i in 0 until elementsAmount) {
                ob.add(i, value)
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun removingEndLinkedList(sizeCollection: Int, _elementsAmount: Int): Long {
            var elementsAmount = _elementsAmount
            val ob = createLinkedList(sizeCollection)
            var targetInd = sizeCollection - 1
            val start = System.currentTimeMillis()
            while (targetInd > 0 && elementsAmount > 0) {
                ob.removeAt(targetInd)
                targetInd--
                elementsAmount--
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun removingMiddleLinkedList(_sizeCollection: Int, _elementsAmount: Int): Long {
            var sizeCollection = _sizeCollection
            var elementsAmount = _elementsAmount
            val ob = createLinkedList(sizeCollection)
            val start = System.currentTimeMillis()
            while (sizeCollection > 0 && elementsAmount > 0) {
                var middleInd = sizeCollection / 2 - 1
                if (middleInd < 0) middleInd = 0
                ob.removeAt(middleInd)
                sizeCollection--
                elementsAmount--
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun removingBeginningLinkedList(_sizeCollection: Int, _elementsAmount: Int): Long {
            var sizeCollection = _sizeCollection
            var elementsAmount = _elementsAmount
            val ob = createLinkedList(sizeCollection)
            val start = System.currentTimeMillis()
            while (sizeCollection > 0 && elementsAmount > 0) {
                ob.removeAt(0)
                sizeCollection--
                elementsAmount--
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun searchByValueLinkedList(sizeCollection: Int, elementsAmount: Int): Long {
            val ob = createLinkedList(sizeCollection)
            val value = 0
            val start = System.currentTimeMillis()
            for (i in 0 until elementsAmount) {
                ob.indexOf(value)
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun addingEndLinkedList(sizeCollection: Int, elementsAmount: Int): Long {
            val ob = createLinkedList(sizeCollection)
            val value = 0
            val newSize = sizeCollection + elementsAmount
            val start = System.currentTimeMillis()
            for (i in sizeCollection until newSize) {
                ob.add(i, value)
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun addingMiddleLinkedList(sizeCollection: Int, elementsAmount: Int): Long {
            val ob = createLinkedList(sizeCollection)
            var startInd = (sizeCollection - 1) / 2
            val endInd = startInd + elementsAmount
            val value = 0
            val start = System.currentTimeMillis()
            while (startInd <= endInd) {
                ob.add(startInd, value)
                startInd++
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun addingBeginningLinkedList(sizeCollection: Int, elementsAmount: Int): Long {
            val ob = createLinkedList(sizeCollection)
            val value = 0
            val start = System.currentTimeMillis()
            for (i in 0 until elementsAmount) {
                ob.add(i, value)
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun removingEndArrayList(sizeCollection: Int, _elementsAmount: Int): Long {
            var elementsAmount = _elementsAmount
            val ob = createArrayList(sizeCollection)
            var targetInd = sizeCollection - 1
            val start = System.currentTimeMillis()
            while (targetInd > 0 && elementsAmount > 0) {
                ob.removeAt(targetInd)
                targetInd--
                elementsAmount--
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun removingMiddleArrayList(_sizeCollection: Int, _elementsAmount: Int): Long {
            var sizeCollection = _sizeCollection
            var elementsAmount = _elementsAmount
            val ob = createArrayList(sizeCollection)
            val start = System.currentTimeMillis()
            while (sizeCollection > 0 && elementsAmount > 0) {
                var middleInd = sizeCollection / 2 - 1
                if (middleInd < 0) middleInd = 0
                ob.removeAt(middleInd)
                sizeCollection--
                elementsAmount--
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun removingBeginningArrayList(_sizeCollection: Int, _elementsAmount: Int): Long {
            var sizeCollection = _sizeCollection
            var elementsAmount = _elementsAmount
            val ob = createArrayList(sizeCollection)
            val start = System.currentTimeMillis()
            while (sizeCollection > 0 && elementsAmount > 0) {
                ob.removeAt(0)
                sizeCollection--
                elementsAmount--
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun searchByValueArrayList(sizeCollection: Int, elementsAmount: Int): Long {
            val ob = createArrayList(sizeCollection)
            val value = 0
            val start = System.currentTimeMillis()
            for (i in 0 until elementsAmount) {
                ob.indexOf(value)
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun addingEndArrayList(sizeCollection: Int, elementsAmount: Int): Long {
            val ob = createArrayList(sizeCollection)
            val value = 0
            val newSize = sizeCollection + elementsAmount
            val start = System.currentTimeMillis()
            for (i in sizeCollection until newSize) {
                ob.add(i, value)
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun addingMiddleArrayList(sizeCollection: Int, elementsAmount: Int): Long {
            val ob = createArrayList(sizeCollection)
            var startInd = (sizeCollection - 1) / 2
            val endInd = startInd + elementsAmount
            val value = 0
            val start = System.currentTimeMillis()
            while (startInd <= endInd) {
                ob.add(startInd, value)
                startInd++
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun addingBeginningArrayList(sizeCollection: Int, elementsAmount: Int): Long {
            val ob = createArrayList(sizeCollection)
            val value = 0
            val start = System.currentTimeMillis()
            for (i in 0 until elementsAmount) {
                ob.add(i, value)
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun removingHashMap(sizeCollection: Int, elementsAmount: Int): Long {
            val ob = createHashMap(sizeCollection)
            val start = System.currentTimeMillis()
            var i = 0
            while (i < elementsAmount && i < sizeCollection) {
                ob.remove(i)
                i++
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun searchingHashMap(sizeCollection: Int, elementsAmount: Int): Long {
            val ob = createHashMap(sizeCollection)
            val targetValue = 0
            val start = System.currentTimeMillis()
            for (i in 0 until elementsAmount) {
                ob[targetValue]
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun addingHashMap(sizeCollection: Int, elementsAmount: Int): Long {
            val ob = createHashMap(sizeCollection)
            val targetValue = 0
            val newSize = sizeCollection + elementsAmount
            val start = System.currentTimeMillis()
            for (i in sizeCollection until newSize) {
                ob[i] = targetValue
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun removingTreeMap(sizeCollection: Int, elementsAmount: Int): Long {
            val ob = createTreeMap(sizeCollection)
            val start = System.currentTimeMillis()
            var i = 0
            while (i < elementsAmount && i < sizeCollection) {
                ob.remove(i)
                i++
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun searchingTreeMap(sizeCollection: Int, elementsAmount: Int): Long {
            val ob = createTreeMap(sizeCollection)
            val targetValue = 0
            val start = System.currentTimeMillis()
            for (i in 0 until elementsAmount) {
                ob[targetValue]
            }
            val end = System.currentTimeMillis()
            return end - start
        }

        private fun addingTreeMap(sizeCollection: Int, elementsAmount: Int): Long {
            val ob = createTreeMap(sizeCollection)
            val targetValue = 0
            val newSize = sizeCollection + elementsAmount
            val start = System.currentTimeMillis()
            for (i in sizeCollection until newSize) {
                ob[i] = targetValue
            }
            val end = System.currentTimeMillis()
            return end - start
        }
    }
}