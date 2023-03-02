package com.magdenbt.collectionsbenchmark;

import android.os.Handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Worker {

    private static Worker worker;
    private ArrayList<? extends OperationListener> operationListeners;
    private int sizeCollection;
    private int elementsAmount;


    private Worker() {
    }

    public static Worker getInstance() {
        if (worker == null) {
            worker = new Worker();
        }
        return worker;
    }


    public void addListeners(ArrayList<? extends OperationListener> operationListeners ) {
        this.operationListeners = operationListeners;
    }

    public void startOperations(int sizeCollection, int elementsAmount, CollectionsType collectionsType, Handler mHandler) throws NullPointerException{
        if (operationListeners == null) throw new NullPointerException();

        this.sizeCollection = sizeCollection;
        this.elementsAmount = elementsAmount;
        switch (collectionsType){
           case LIST:
              listOperationsInvoker(mHandler);
              break;
           case MAP:
               mapOperationsInvoker(mHandler);
               break;
                 }
        }

    private void listOperationsInvoker(Handler mHandler) throws NullPointerException{
        if (operationListeners == null) throw new NullPointerException();

        ListOperationsMethodsContainer methodsContainer = new ListOperationsMethodsContainer();
        Method[] operations = methodsContainer.getClass().getDeclaredMethods();
        for (Method operation :
                operations) {

            new Thread(() -> mHandler.post(()->{
            try {
                operation.invoke(methodsContainer);
            } catch (IllegalAccessException e) {
                e.getMessage();
            } catch (InvocationTargetException e) {
                e.getMessage();
            }catch (Exception e){
                e.getMessage();
            }
            })).start();
        }

    }

    private void mapOperationsInvoker(Handler mHandler) throws NullPointerException{
        if (operationListeners == null) throw new NullPointerException();

        MapOperationsMethodsContainer methodsContainer = new MapOperationsMethodsContainer();
        Method[] operations = methodsContainer.getClass().getDeclaredMethods();
        for (Method operation :
                operations) {
            new Thread(() -> mHandler.post(()->{
                try {
                    operation.invoke(methodsContainer);
                } catch (IllegalAccessException e) {
                    e.getMessage();
                } catch (InvocationTargetException e) {
                    e.getMessage();
                }catch (Exception e){
                    e.getMessage();
                }
            })).start();
        }

    }




    private synchronized void sendResult(Class aClass, OperationTypes operationTypes, long duration) {
        for (OperationListener operationListener :
                operationListeners) {
            operationListener.dataOperationComplete(aClass, operationTypes, duration);
        }
    }

    private synchronized HashMap<Integer, Integer> createHashMap() {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < sizeCollection; i++)  {
            hashMap.put(i,0);
        }
        return hashMap;
    }

    private synchronized TreeMap<Integer, Integer> createTreeMap() {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < sizeCollection; i++)  {
            treeMap.put(i,0);
        }
        return treeMap;
    }

    private synchronized ArrayList<Integer> createArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < sizeCollection; i++) {
            arrayList.add(0);
        }
        return arrayList;
    }

    private synchronized CopyOnWriteArrayList<Integer> createCopyOnWriteArrayList() {

        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < sizeCollection; i++) {
            copyOnWriteArrayList.add(0);
        }
        return copyOnWriteArrayList;
    }

    private synchronized LinkedList<Integer> createLinkedList() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < sizeCollection; i++) {
            linkedList.add(0);
        }
        return linkedList;
    }



    private class ListOperationsMethodsContainer {


        protected void removingEndCopyOnWriteArrayList() {
            CopyOnWriteArrayList<Integer> ob = createCopyOnWriteArrayList();
            int targetInd = sizeCollection - 1;
            long start = System.currentTimeMillis();
            for (; targetInd >= elementsAmount; targetInd-- ){
                ob.remove(targetInd);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.REMOVINGINTHEEND, end - start);
            ob = null;
        }

        protected void removingMiddleCopyOnWriteArrayList() {
            CopyOnWriteArrayList<Integer> ob = createCopyOnWriteArrayList();
            int targetInd = (sizeCollection - 1) / 2;
            long start = System.currentTimeMillis();
            for (; targetInd <= elementsAmount; targetInd++ ){
                ob.remove(targetInd);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.REMOVINGINTHEMIDDLE, end - start);
            ob = null;
        }

        protected void removingBeginningCopyOnWriteArrayList() {
            CopyOnWriteArrayList<Integer> ob = createCopyOnWriteArrayList();
            long start = System.currentTimeMillis();
            for (int i = 0; i < elementsAmount; i++) {
                ob.remove(i);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.REMOVINGINTHEBEGINNING, end - start);
            ob = null;
        }

        protected void searchByValueCopyOnWriteArrayList() {
            CopyOnWriteArrayList<Integer> ob = createCopyOnWriteArrayList();
            Integer value = 0;
            long start = System.currentTimeMillis();
            for (int i = 0; i < elementsAmount; i++) {
                ob.indexOf(value);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.SEARCHBYVALUE, end - start);
            ob = null;
        }

        protected void addingEndCopyOnWriteArrayList() {
            CopyOnWriteArrayList<Integer> ob = createCopyOnWriteArrayList();
            Integer value = 0;
            long start = System.currentTimeMillis();
            for (int i = sizeCollection - 1; i < elementsAmount; i++) {
                ob.add(i, value);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.ADDINGINTHEEND, end - start);
            ob = null;
        }

        protected void addingMiddleCopyOnWriteArrayList() {
            CopyOnWriteArrayList<Integer> ob = createCopyOnWriteArrayList();
            Integer value = 0;
            int startInd = (sizeCollection - 1) / 2;
            int endInd = startInd + elementsAmount;
            long start = System.currentTimeMillis();
            for (;  startInd <= endInd; startInd++) {
                ob.add(startInd, value);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.ADDINGINTHEMIDDLE, end - start);
            ob = null;
        }

        protected void addingBeginningCopyOnWriteArrayList() {
            CopyOnWriteArrayList<Integer> ob = createCopyOnWriteArrayList();
            Integer value = 0;
            long start = System.currentTimeMillis();
            for (int i = 0;  i < elementsAmount; i++) {
                ob.add(i, value);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.ADDINGINTHEBEGINNING, end - start);
            ob = null;
        }

        protected void removingEndLinkedList() {
            LinkedList<Integer> ob = createLinkedList();
            int targetInd = sizeCollection - 1;
            long start = System.currentTimeMillis();
            for (; targetInd >= elementsAmount; targetInd-- ){
                ob.remove(targetInd);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.REMOVINGINTHEEND, end - start);
            ob = null;
        }

        protected void removingMiddleLinkedList() {
            LinkedList<Integer> ob = createLinkedList();
            int targetInd = (sizeCollection - 1) / 2;
            long start = System.currentTimeMillis();
            for (; targetInd <= elementsAmount; targetInd++ ){
                ob.remove(targetInd);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.REMOVINGINTHEMIDDLE, end - start);
            ob = null;

        }

        protected void removingBeginningLinkedList() {
            LinkedList<Integer> ob = createLinkedList();
            long start = System.currentTimeMillis();
            for (int i = 0; i < elementsAmount; i++) {
                ob.remove(i);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.REMOVINGINTHEBEGINNING, end - start);
            ob = null;

        }

        protected void searchByValueLinkedList() {
            LinkedList<Integer> ob = createLinkedList();
            Integer value = 0;
            long start = System.currentTimeMillis();
            for (int i = 0; i < elementsAmount; i++) {
                ob.indexOf(value);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.SEARCHBYVALUE, end - start);
            ob = null;

        }

        protected void addingEndLinkedList() {
            LinkedList<Integer> ob = createLinkedList();
            Integer value = 0;
            long start = System.currentTimeMillis();
            for (int i = sizeCollection - 1; i < elementsAmount; i++) {
                ob.add(i, value);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.ADDINGINTHEEND, end - start);
            ob = null;

        }

        protected void addingMiddleLinkedList() {
            LinkedList<Integer> ob = createLinkedList();
            int startInd = (sizeCollection - 1) / 2;
            int endInd = startInd + elementsAmount;
            Integer value = 0;
            long start = System.currentTimeMillis();
            for (;  startInd <= endInd; startInd++) {
                ob.add(startInd, value);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.ADDINGINTHEMIDDLE, end - start);
            ob = null;

        }

        protected void addingBeginningLinkedList() {
            LinkedList<Integer> ob = createLinkedList();
            Integer value = 0;
            long start = System.currentTimeMillis();
            for (int i = 0;  i < elementsAmount; i++) {
                ob.add(i, value);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.ADDINGINTHEBEGINNING, end - start);
            ob = null;

        }

        protected void removingEndArrayList() {
            ArrayList<Integer> ob = createArrayList();
            int targetInd = sizeCollection - 1;
            long start = System.currentTimeMillis();
            for (; targetInd >= elementsAmount; targetInd-- ){
                ob.remove(targetInd);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.REMOVINGINTHEEND, end - start);
            ob = null;

        }

        protected void removingMiddleArrayList() {
            ArrayList<Integer> ob = createArrayList();
            int targetInd = (sizeCollection - 1) / 2;
            long start = System.currentTimeMillis();
            for (; targetInd <= elementsAmount; targetInd++ ){
                ob.remove(targetInd);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.REMOVINGINTHEMIDDLE, end - start);
            ob = null;

        }

        protected void removingBeginningArrayList() {
            ArrayList<Integer> ob = createArrayList();
            long start = System.currentTimeMillis();
            for (int i = 0; i < elementsAmount; i++) {
                ob.remove(i);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.REMOVINGINTHEBEGINNING, end - start);
            ob = null;

        }

        protected void searchByValueArrayList() {
            ArrayList<Integer> ob = createArrayList();
            Integer value = 0;
            long start = System.currentTimeMillis();
            for (int i = 0; i < elementsAmount; i++) {
                ob.indexOf(value);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.SEARCHBYVALUE, end - start);
            ob = null;

        }

        protected void addingEndArrayList() {
            ArrayList<Integer> ob = createArrayList();
            Integer value = 0;
            long start = System.currentTimeMillis();
            for (int i = sizeCollection - 1; i < elementsAmount; i++) {
                ob.add(i, value);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.ADDINGINTHEEND, end - start);
            ob = null;

        }

        protected void addingMiddleArrayList() {
            ArrayList<Integer> ob = createArrayList();
            int startInd = (sizeCollection - 1) / 2;
            int endInd = startInd + elementsAmount;
            Integer value = 0;
            long start = System.currentTimeMillis();
            for (;  startInd <= endInd; startInd++) {
                ob.add(startInd, value);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.ADDINGINTHEMIDDLE, end - start);
            ob = null;

        }

        protected void addingBeginningArrayList() {
            ArrayList<Integer> ob = createArrayList();
            Integer value = 0;
            long start = System.currentTimeMillis();
            for (int i = 0;  i < elementsAmount; i++) {
                ob.add(i, value);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.ADDINGINTHEBEGINNING, end - start);
            ob = null;
        }


    }

    private class MapOperationsMethodsContainer {


        protected void removingHashMap() {
            HashMap<Integer, Integer> ob = createHashMap();
            long start = System.currentTimeMillis();
            for (int i = 0; i < elementsAmount; i++) {
                ob.remove(i);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.REMOVING, end - start);
            ob = null;
        }

        protected void searchingHashMap() {
            HashMap<Integer, Integer> ob = createHashMap();
            Integer targetValue = 0;
            long start = System.currentTimeMillis();
            for (int i = 0; i < elementsAmount; i++) {
                ob.get(targetValue);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.SEARCHBYKEY, end - start);
            ob = null;
        }

        protected void addingHashMap() {
            HashMap<Integer, Integer> ob = createHashMap();
            Integer targetValue = 0;
            long start = System.currentTimeMillis();
            for (int i = sizeCollection - 1; i < elementsAmount; i++) {
                ob.put(i, targetValue);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.ADDINGNEW, end - start);
            ob = null;
        }

        protected void removingTreeMap() {
            TreeMap<Integer, Integer> ob = createTreeMap();
            long start = System.currentTimeMillis();
            for (int i = 0; i < elementsAmount; i++) {
                ob.remove(i);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.REMOVING, end - start);
            ob = null;
        }

        protected void searchingTreeMap() {
            TreeMap<Integer, Integer> ob = createTreeMap();
            Integer targetValue = 0;
            long start = System.currentTimeMillis();
            for (int i = 0; i < elementsAmount; i++) {
                ob.get(targetValue);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.SEARCHBYKEY, end - start);
            ob = null;
        }

        protected void addingTreeMap() {
            TreeMap<Integer, Integer> ob = createTreeMap();
            Integer targetValue = 0;
            long start = System.currentTimeMillis();
            for (int i = sizeCollection - 1; i < elementsAmount; i++) {
                ob.put(i, targetValue);
            }
            long end = System.currentTimeMillis();
            sendResult(ob.getClass(), OperationTypes.ADDINGNEW, end - start);
            ob = null;
        }



    }

}
