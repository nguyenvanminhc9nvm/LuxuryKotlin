package com.example.luxurykotlin.ui.base

import androidx.lifecycle.ViewModel
import com.example.luxurykotlin.data.DataManager
import com.example.luxurykotlin.utils.scheduler.SchedulerProvider
import com.example.luxurykotlin.utils.tracking.RxProgressBar
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by OpenYourEyes on 11/26/2019
 */
/**
 * I : Input transform
 * O : Output transform
 */

open abstract class BaseViewModel<I, O> : ViewModel() {
    lateinit var mDataManager: DataManager

    lateinit var mScheduler: SchedulerProvider

    lateinit var mNavigatorActivity: INavigatorActivity

    private val mCompositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    val mProgressBar: RxProgressBar by lazy {
        RxProgressBar(mScheduler)
    }

    abstract fun transform(input: I): O

    open fun initData(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider
    ) {
        this.mDataManager = dataManager
        this.mScheduler = schedulerProvider
    }


    override fun onCleared() {
        mCompositeDisposable.clear()
        super.onCleared()
    }

    fun Disposable.addToDisposable() {
        mCompositeDisposable.add(this)
    }

    fun onDestroyView() {
        mCompositeDisposable.clear()
    }


}