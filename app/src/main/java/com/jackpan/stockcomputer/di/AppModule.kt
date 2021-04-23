package com.jackpan.stockcomputer.di

import android.app.Application
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jackpan.stockcomputer.MyApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun getAppInstance(application: Application) : MyApp {
        return application as MyApp
    }

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl("")
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun getFireXXX(fire: Firebase) : FirebaseUser? {

        return  fire.auth.currentUser
    }

}