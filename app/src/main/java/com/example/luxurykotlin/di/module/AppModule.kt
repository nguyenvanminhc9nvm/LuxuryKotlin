package com.example.luxurykotlin.di.module

import AppSchedulerProvider
import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import com.example.luxurykotlin.data.AppDataManager
import com.example.luxurykotlin.data.DataManager
import com.example.luxurykotlin.data.local.AppPreferencesHelper
import com.example.luxurykotlin.data.local.PreferenceHelper
import com.example.luxurykotlin.data.remote.ApiHelper
import com.example.luxurykotlin.data.remote.AppApiHelper
import com.example.luxurykotlin.di.Preferences
import com.example.luxurykotlin.utils.scheduler.SchedulerProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module 
class AppModule {
    @Provides
    @Singleton
    fun binResource(application: Application): Resources {
        return application.resources
    }


    @Provides
    @Singleton
    fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory {
        return factory
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    fun provideAppSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }


    @Provides
    @Preferences
    @Singleton
    fun providePreferenceName(): String {
        return "pref_app_name"
    }

    @Provides
    @Singleton

    fun providerPreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferenceHelper {
        return appPreferencesHelper
    }



    @Provides

    @Singleton
    fun provideApiSearvice(): ApiHelper {
        return AppApiHelper()
    }

    @Provides

    @Singleton
    fun provideGson(): Gson? {
        return GsonBuilder().setLenient().create()
    }

}