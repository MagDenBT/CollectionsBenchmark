package com.magdenbt.collectionsbenchmark;

public enum OperationTypes {
    ADDINGINTHEBEGINNING(CollectionsType.LIST),
    ADDINGINTHEMIDDLE(CollectionsType.LIST),
    ADDINGINTHEEND(CollectionsType.LIST),
    SEARCHBYVALUE(CollectionsType.LIST),
    REMOVINGINTHEBEGINNING(CollectionsType.LIST),
    REMOVINGINTHEMIDDLE(CollectionsType.LIST),
    REMOVINGINTHEEND(CollectionsType.LIST),
    ADDINGNEW(CollectionsType.MAP),
    SEARCHBYKEY(CollectionsType.MAP),
    REMOVING(CollectionsType.MAP),
    ;

    final CollectionsType collectionsType;
    OperationTypes(CollectionsType collectionType) {
        this.collectionsType = collectionType;
    }
}
