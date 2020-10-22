package com.example.luxurykotlin.di.component

import android.app.Application
import com.example.luxurykotlin.BaseApplication
import com.example.luxurykotlin.di.builder.Builder
import com.example.luxurykotlin.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by OpenYourEyes on 2/4/2020
 */
@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class, Builder::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}