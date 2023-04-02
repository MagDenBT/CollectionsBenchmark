package com.magdenbt.collectionsbenchmark.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MainActivityQ()

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewPagerFragmentQ()
