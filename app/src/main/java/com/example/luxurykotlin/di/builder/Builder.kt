package com.example.luxurykotlin.di.builder

import com.example.luxurykotlin.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class Builder {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}