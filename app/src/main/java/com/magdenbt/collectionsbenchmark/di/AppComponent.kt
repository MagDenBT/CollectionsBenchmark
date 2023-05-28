package com.magdenbt.collectionsbenchmark.di

import android.app.Application
import android.content.Context
import com.magdenbt.collectionsbenchmark.di.mainactivity.MainComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubcomponents::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance application: Application
        ): AppComponent
    }

    fun mainComponent(): MainComponent.Factory

}

@Module(subcomponents = [MainComponent::class])
class AppSubcomponents

