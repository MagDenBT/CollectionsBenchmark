package com.magdenbt.collectionsbenchmark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.rxjava3.core.Single;


public class Benchmark {


    public static Single<Long> createOb(int sizeCollection, int elementsAmount, OperationTypes operationType) {
        return Single.create(emitter -> emitter.onSuccess(calculateDuration(sizeCollection, elementsAmount, operationType)));
    }


    private static long calculateDuration(int sizeCollection, int elementsAmount, OperationTypes operationType) {

        switch (operationType) {

            case ARRAY_LIST_ADDING_IN_THE_BEGINNING:
                return addingBeginningArrayList(sizeCollection, elementsAmount);

            case ARRAY_LIST_ADDING_IN_THE_MIDDLE:
                return addingMiddleArrayList(sizeCollection, elementsAmount);

            case ARRAY_LIST_ADDING_IN_THE_END:
                return addingEndArrayList(sizeCollection, elementsAmount);

            case ARRAY_LIST_SEARCH_BY_VALUE:
                return searchByValueArrayList(sizeCollection, elementsAmount);

            case ARRAY_LIST_REMOVING_IN_THE_BEGINNING:
                return removingBeginningArrayList(sizeCollection, elementsAmount);

            case ARRAY_LIST_REMOVING_IN_THE_MIDDLE:
                return removingMiddleArrayList(sizeCollection, elementsAmount);

            case ARRAY_LIST_REMOVING_IN_THE_END:
                return removingEndArrayList(sizeCollection, elementsAmount);


            case LINKED_LIST_ADDING_IN_THE_BEGINNING:
                return addingBeginningLinkedList(sizeCollection, elementsAmount);

            case LINKED_LIST_ADDING_IN_THE_MIDDLE:
                return addingMiddleLinkedList(sizeCollection, elementsAmount);

            case LINKED_LIST_ADDING_IN_THE_END:
                return addingEndLinkedList(sizeCollection, elementsAmount);

            case LINKED_LIST_SEARCH_BY_VALUE:
                return searchByValueLinkedList(sizeCollection, elementsAmount);

            case LINKED_LIST_REMOVING_IN_THE_BEGINNING:
                return removingBeginningLinkedList(sizeCollection, elementsAmount);

            case LINKED_LIST_REMOVING_IN_THE_MIDDLE:
                return removingMiddleLinkedList(sizeCollection, elementsAmount);

            case LINKED_LIST_REMOVING_IN_THE_END:
                return removingEndLinkedList(sizeCollection, elementsAmount);


            case COPY_ON_WRITE_AL_ADDING_IN_THE_BEGINNING:
                return addingBeginningCopyOnWriteArrayList(sizeCollection, elementsAmount);

            case COPY_ON_WRITE_AL_ADDING_IN_THE_MIDDLE:
                return addingMiddleCopyOnWriteArrayList(sizeCollection, elementsAmount);

            case COPY_ON_WRITE_AL_ADDING_IN_THE_END:
                return addingEndCopyOnWriteArrayList(sizeCollection, elementsAmount);

            case COPY_ON_WRITE_AL_SEARCH_BY_VALUE:
                return searchByValueCopyOnWriteArrayList(sizeCollection, elementsAmount);

            case COPY_ON_WRITE_AL_REMOVING_IN_THE_BEGINNING:
                return removingBeginningCopyOnWriteArrayList(sizeCollection, elementsAmount);

            case COPY_ON_WRITE_AL_REMOVING_IN_THE_MIDDLE:
                return removingMiddleCopyOnWriteArrayList(sizeCollection, elementsAmount);

            case COPY_ON_WRITE_AL_REMOVING_IN_THE_END:
                return removingEndCopyOnWriteArrayList(sizeCollection, elementsAmount);


            case HASH_MAP_ADDING_NEW:
                return addingHashMap(sizeCollection, elementsAmount);

            case HASH_MAP_SEARCH_BY_KEY:
                return searchingHashMap(sizeCollection, elementsAmount);

            case HASH_MAP_REMOVING:
                return removingHashMap(sizeCollection, elementsAmount);


            case TREE_MAP_ADDING_NEW:
                return addingTreeMap(sizeCollection, elementsAmount);

            case TREE_MAP_SEARCH_BY_KEY:
                return searchingTreeMap(sizeCollection, elementsAmount);

            case TREE_MAP_REMOVING:
                return removingTreeMap(sizeCollection, elementsAmount);

            default:
                return -1;
        }
    }


    private static HashMap<Integer, Integer> createHashMap(int sizeCollection) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < sizeCollection; i++) {
            hashMap.put(i, 0);
        }
        return hashMap;
    }

    private static TreeMap<Integer, Integer> createTreeMap(int sizeCollection) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < sizeCollection; i++) {
            treeMap.put(i, 0);
        }
        return treeMap;
    }

    private static ArrayList<Integer> createArrayList(int sizeCollection) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < sizeCollection; i++) {
            arrayList.add(0);
        }
        return arrayList;
    }

    private static CopyOnWriteArrayList<Integer> createCopyOnWriteArrayList(int sizeCollection) {

        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < sizeCollection; i++) {
            copyOnWriteArrayList.add(0);
        }
        return copyOnWriteArrayList;
    }

    private static LinkedList<Integer> createLinkedList (int sizeCollection) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < sizeCollection; i++) {
            linkedList.add(0);
        }
        return linkedList;
    }


    private static long removingEndCopyOnWriteArrayList(int sizeCollection, int elementsAmount) {
        CopyOnWriteArrayList<Integer> ob = createCopyOnWriteArrayList(sizeCollection);
        int targetInd = sizeCollection - 1;
        long start = System.currentTimeMillis();
        for (; targetInd >= elementsAmount; targetInd--) {
            ob.remove(targetInd);
        }
        long end = System.currentTimeMillis();

        return end - start;

    }

    private static long removingMiddleCopyOnWriteArrayList(int sizeCollection, int elementsAmount) {
        CopyOnWriteArrayList<Integer> ob = createCopyOnWriteArrayList(sizeCollection);
        int targetInd = (sizeCollection - 1) / 2;
        long start = System.currentTimeMillis();
        for (; targetInd <= elementsAmount; targetInd++) {
            ob.remove(targetInd);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long removingBeginningCopyOnWriteArrayList(int sizeCollection, int elementsAmount) {
        CopyOnWriteArrayList<Integer> ob = createCopyOnWriteArrayList(sizeCollection);
        long start = System.currentTimeMillis();
        for (int i = 0; i < elementsAmount; i++) {
            ob.remove(i);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long searchByValueCopyOnWriteArrayList(int sizeCollection, int elementsAmount) {
        CopyOnWriteArrayList<Integer> ob = createCopyOnWriteArrayList(sizeCollection);
        Integer value = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < elementsAmount; i++) {
            ob.indexOf(value);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long addingEndCopyOnWriteArrayList(int sizeCollection, int elementsAmount) {
        CopyOnWriteArrayList<Integer> ob = createCopyOnWriteArrayList(sizeCollection);
        Integer value = 0;
        long start = System.currentTimeMillis();
        for (int i = sizeCollection - 1; i < elementsAmount; i++) {
            ob.add(i, value);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long addingMiddleCopyOnWriteArrayList(int sizeCollection, int elementsAmount) {
        CopyOnWriteArrayList<Integer> ob = createCopyOnWriteArrayList(sizeCollection);
        Integer value = 0;
        int startInd = (sizeCollection - 1) / 2;
        int endInd = startInd + elementsAmount;
        long start = System.currentTimeMillis();
        for (; startInd <= endInd; startInd++) {
            ob.add(startInd, value);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long addingBeginningCopyOnWriteArrayList(int sizeCollection, int elementsAmount) {
        CopyOnWriteArrayList<Integer> ob = createCopyOnWriteArrayList(sizeCollection);
        Integer value = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < elementsAmount; i++) {
            ob.add(i, value);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long removingEndLinkedList(int sizeCollection, int elementsAmount) {
        LinkedList<Integer> ob = createLinkedList(sizeCollection);
        int targetInd = sizeCollection - 1;
        long start = System.currentTimeMillis();
        for (; targetInd >= elementsAmount; targetInd--) {
            ob.remove(targetInd);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long removingMiddleLinkedList(int sizeCollection, int elementsAmount) {
        LinkedList<Integer> ob = createLinkedList(sizeCollection);
        int targetInd = (sizeCollection - 1) / 2;
        long start = System.currentTimeMillis();
        for (; targetInd <= elementsAmount; targetInd++) {
            ob.remove(targetInd);
        }
        long end = System.currentTimeMillis();

        return end - start;

    }

    private static long removingBeginningLinkedList(int sizeCollection, int elementsAmount) {
        LinkedList<Integer> ob = createLinkedList(sizeCollection);
        long start = System.currentTimeMillis();
        for (int i = 0; i < elementsAmount; i++) {
            ob.remove(i);
        }
        long end = System.currentTimeMillis();

        return end - start;

    }

    private static long searchByValueLinkedList(int sizeCollection, int elementsAmount) {
        LinkedList<Integer> ob = createLinkedList(sizeCollection);
        Integer value = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < elementsAmount; i++) {
            ob.indexOf(value);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long addingEndLinkedList(int sizeCollection, int elementsAmount) {
        LinkedList<Integer> ob = createLinkedList(sizeCollection);
        Integer value = 0;
        long start = System.currentTimeMillis();
        for (int i = sizeCollection - 1; i < elementsAmount; i++) {
            ob.add(i, value);
        }
        long end = System.currentTimeMillis();

        return end - start;

    }

    private static long addingMiddleLinkedList(int sizeCollection, int elementsAmount) {
        LinkedList<Integer> ob = createLinkedList(sizeCollection);
        int startInd = (sizeCollection - 1) / 2;
        int endInd = startInd + elementsAmount;
        Integer value = 0;
        long start = System.currentTimeMillis();
        for (; startInd <= endInd; startInd++) {
            ob.add(startInd, value);
        }
        long end = System.currentTimeMillis();

        return end - start;

    }

    private static long addingBeginningLinkedList(int sizeCollection, int elementsAmount) {
        LinkedList<Integer> ob = createLinkedList(sizeCollection);
        Integer value = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < elementsAmount; i++) {
            ob.add(i, value);
        }
        long end = System.currentTimeMillis();

        return end - start;

    }

    private static long removingEndArrayList(int sizeCollection, int elementsAmount) {
        ArrayList<Integer> ob = createArrayList(sizeCollection);
        int targetInd = sizeCollection - 1;
        long start = System.currentTimeMillis();
        for (; targetInd >= elementsAmount; targetInd--) {
            ob.remove(targetInd);
        }
        long end = System.currentTimeMillis();

        return end - start;

    }

    private static long removingMiddleArrayList(int sizeCollection, int elementsAmount) {
        ArrayList<Integer> ob = createArrayList(sizeCollection);
        int targetInd = (sizeCollection - 1) / 2;
        long start = System.currentTimeMillis();
        for (; targetInd <= elementsAmount; targetInd++) {
            ob.remove(targetInd);
        }
        long end = System.currentTimeMillis();

        return end - start;

    }

    private static long removingBeginningArrayList(int sizeCollection, int elementsAmount) {
        ArrayList<Integer> ob = createArrayList(sizeCollection);
        long start = System.currentTimeMillis();
        for (int i = 0; i < elementsAmount; i++) {
            ob.remove(i);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long searchByValueArrayList(int sizeCollection, int elementsAmount) {
        ArrayList<Integer> ob = createArrayList(sizeCollection);
        Integer value = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < elementsAmount; i++) {
            ob.indexOf(value);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long addingEndArrayList(int sizeCollection, int elementsAmount) {
        ArrayList<Integer> ob = createArrayList(sizeCollection);
        Integer value = 0;
        long start = System.currentTimeMillis();
        for (int i = sizeCollection - 1; i < elementsAmount; i++) {
            ob.add(i, value);
        }
        long end = System.currentTimeMillis();

        return end - start;

    }

    private static long addingMiddleArrayList(int sizeCollection, int elementsAmount) {
        ArrayList<Integer> ob = createArrayList(sizeCollection);
        int startInd = (sizeCollection - 1) / 2;
        int endInd = startInd + elementsAmount;
        Integer value = 0;
        long start = System.currentTimeMillis();
        for (; startInd <= endInd; startInd++) {
            ob.add(startInd, value);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long addingBeginningArrayList(int sizeCollection, int elementsAmount) {
        ArrayList<Integer> ob = createArrayList(sizeCollection);
        Integer value = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < elementsAmount; i++) {
            ob.add(i, value);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long removingHashMap(int sizeCollection, int elementsAmount) {
        HashMap<Integer, Integer> ob = createHashMap(sizeCollection);
        long start = System.currentTimeMillis();
        for (int i = 0; i < elementsAmount; i++) {
            ob.remove(i);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long searchingHashMap(int sizeCollection, int elementsAmount) {
        HashMap<Integer, Integer> ob = createHashMap(sizeCollection);
        Integer targetValue = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < elementsAmount; i++) {
            ob.get(targetValue);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long addingHashMap(int sizeCollection, int elementsAmount) {
        HashMap<Integer, Integer> ob = createHashMap(sizeCollection);
        Integer targetValue = 0;
        long start = System.currentTimeMillis();
        for (int i = sizeCollection - 1; i < elementsAmount; i++) {
            ob.put(i, targetValue);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long removingTreeMap(int sizeCollection, int elementsAmount) {
        TreeMap<Integer, Integer> ob = createTreeMap(sizeCollection);
        long start = System.currentTimeMillis();
        for (int i = 0; i < elementsAmount; i++) {
            ob.remove(i);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long searchingTreeMap(int sizeCollection, int elementsAmount) {
        TreeMap<Integer, Integer> ob = createTreeMap(sizeCollection);
        Integer targetValue = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < elementsAmount; i++) {
            ob.get(targetValue);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static long addingTreeMap(int sizeCollection, int elementsAmount) {
        TreeMap<Integer, Integer> ob = createTreeMap(sizeCollection);
        Integer targetValue = 0;
        long start = System.currentTimeMillis();
        for (int i = sizeCollection - 1; i < elementsAmount; i++) {
            ob.put(i, targetValue);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

}


