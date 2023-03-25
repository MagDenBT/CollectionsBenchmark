package com.magdenbt.collectionsbenchmark.modelflow;

import com.magdenbt.collectionsbenchmark.CollectionsType;

public enum OperationTypes {
    ARRAY_LIST_ADDING_IN_THE_BEGINNING(CollectionsType.LIST),
    ARRAY_LIST_ADDING_IN_THE_MIDDLE(CollectionsType.LIST),
    ARRAY_LIST_ADDING_IN_THE_END(CollectionsType.LIST),
    ARRAY_LIST_SEARCH_BY_VALUE(CollectionsType.LIST),
    ARRAY_LIST_REMOVING_IN_THE_BEGINNING(CollectionsType.LIST),
    ARRAY_LIST_REMOVING_IN_THE_MIDDLE(CollectionsType.LIST),
    ARRAY_LIST_REMOVING_IN_THE_END(CollectionsType.LIST),

    LINKED_LIST_ADDING_IN_THE_BEGINNING(CollectionsType.LIST),
    LINKED_LIST_ADDING_IN_THE_MIDDLE(CollectionsType.LIST),
    LINKED_LIST_ADDING_IN_THE_END(CollectionsType.LIST),
    LINKED_LIST_SEARCH_BY_VALUE(CollectionsType.LIST),
    LINKED_LIST_REMOVING_IN_THE_BEGINNING(CollectionsType.LIST),
    LINKED_LIST_REMOVING_IN_THE_MIDDLE(CollectionsType.LIST),
    LINKED_LIST_REMOVING_IN_THE_END(CollectionsType.LIST),

    COPY_ON_WRITE_AL_ADDING_IN_THE_BEGINNING(CollectionsType.LIST),
    COPY_ON_WRITE_AL_ADDING_IN_THE_MIDDLE(CollectionsType.LIST),
    COPY_ON_WRITE_AL_ADDING_IN_THE_END(CollectionsType.LIST),
    COPY_ON_WRITE_AL_SEARCH_BY_VALUE(CollectionsType.LIST),
    COPY_ON_WRITE_AL_REMOVING_IN_THE_BEGINNING(CollectionsType.LIST),
    COPY_ON_WRITE_AL_REMOVING_IN_THE_MIDDLE(CollectionsType.LIST),
    COPY_ON_WRITE_AL_REMOVING_IN_THE_END(CollectionsType.LIST),

    HASH_MAP_ADDING_NEW(CollectionsType.MAP),
    HASH_MAP_SEARCH_BY_KEY(CollectionsType.MAP),
    HASH_MAP_REMOVING(CollectionsType.MAP),

    TREE_MAP_ADDING_NEW(CollectionsType.MAP),
    TREE_MAP_SEARCH_BY_KEY(CollectionsType.MAP),
    TREE_MAP_REMOVING(CollectionsType.MAP),
    ;

    final CollectionsType collectionsType;
    OperationTypes(CollectionsType collectionType) {
        this.collectionsType = collectionType;
    }
}
