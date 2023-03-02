package com.magdenbt.collectionsbenchmark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public enum CollectionsType {
    LIST{
        public Class[] supportClasses(){
            return new Class[]{ArrayList.class, LinkedList.class, CopyOnWriteArrayList.class};
        }
    },
    MAP{
        public Class[] supportClasses(){
            return new Class[]{HashMap.class, TreeMap.class};
        }
    }
;
    public abstract Class[] supportClasses();

}
