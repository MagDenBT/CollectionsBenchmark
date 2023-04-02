package com.magdenbt.collectionsbenchmark

import android.app.Application

import com.magdenbt.collectionsbenchmark.di.DaggerAppComponent

class InitApp : Application() {

    val appComponent by lazy {
        initializeAppComponent()
    }

    private fun initializeAppComponent() =
        DaggerAppComponent.factory().create(applicationContext, this)
}