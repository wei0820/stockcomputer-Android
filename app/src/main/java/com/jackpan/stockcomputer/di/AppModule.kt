package com.jackpan.stockcomputer.di

import android.app.Application
import com.jackpan.stockcomputer.MyApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun getAppInstance(application: Application) : MyApp {
        return application as MyApp
    }
}