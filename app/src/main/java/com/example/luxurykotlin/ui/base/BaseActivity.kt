package com.example.luxurykotlin.ui.base

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.os.Bundle
import android.view.Window
import android.view.inputmethod.InputMethodManager
import com.example.luxurykotlin.R
import com.example.luxurykotlin.data.DataManager
import com.example.luxurykotlin.di.module.ViewModelFactory
import com.example.luxurykotlin.utils.scheduler.SchedulerProvider
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject


/**
 * Created by OpenYourEyes on 01/03/2020
 */
open abstract class BaseActivity<VM : BaseViewModel<*, *>> : DaggerAppCompatActivity() {

    var isNetworkAvailable: Boolean = false

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    @Inject
    lateinit var dataManager: DataManager

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val compositeDisposable = CompositeDisposable()

    private lateinit var mProgressDialog: ProgressDialog

    lateinit var viewModel: VM

    abstract fun createViewModel(): Class<VM>

    abstract fun getContentView(): Int

    abstract fun initView()

    abstract fun bindViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mProgressDialog = ProgressDialog(this)
        viewModel = viewModelFactory.create(createViewModel())
        setContentView(getContentView())
        initView()
        bindLoading()
        bindViewModel()

    }

    fun bindLoading(){
        viewModel.mProgressBar.subscribeOn(schedulerProvider.ui).subscribe {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        }.addToDisposable()
    }

    fun Disposable.addToDisposable() {
        compositeDisposable.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    fun hideSoftKeyboard() {
        currentFocus?.let {
            val inputMethodManager =
                getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }


    private var networkCallback: NetworkCallback = object : NetworkCallback() {
        override fun onAvailable(network: Network) {
            // network available
            println("DKS onAvailable")
            isNetworkAvailable = true
        }

        override fun onLost(network: Network) {
            // network unavailable
            isNetworkAvailable = false
        }
    }


    private fun showLoading() {
        if (!mProgressDialog.isShowing ) {
            mProgressDialog.show()
        }
    }

    private fun hideLoading() {
        if (mProgressDialog.isShowing ) {
            mProgressDialog.dismiss()
        }
    }

    class ProgressDialog constructor(context: Context) : Dialog(context) {
        init {
            setCancelable(false)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_progress)
            val window = window
            if (window != null) {
                getWindow()!!.setBackgroundDrawableResource(android.R.color.transparent)
            }
        }
    }

}
