package com.example.luxurykotlin.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.luxurykotlin.data.DataManager
import com.example.luxurykotlin.ui.base.BaseViewModel
import com.example.luxurykotlin.ui.main.MainViewModel
import com.example.luxurykotlin.utils.scheduler.SchedulerProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val dataManager: DataManager
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass) {
            MainViewModel::class.java -> MainViewModel() as T
            else -> throw IllegalArgumentException("Unknown ViewModel class ${modelClass.simpleName}")
        }
        if (viewModel is BaseViewModel<*, *>) {
            viewModel.initData(dataManager, schedulerProvider)
        }
        return viewModel
    }
}