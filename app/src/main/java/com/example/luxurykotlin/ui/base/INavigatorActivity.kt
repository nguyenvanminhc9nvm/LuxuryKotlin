package com.example.luxurykotlin.ui.base

import android.os.Bundle
import androidx.annotation.IdRes
import kotlin.reflect.KClass

/**
 * Created by OpenYourEyes on 1/13/2020
 */
interface INavigatorActivity {

    fun switchFragment(
        fragment: KClass<*>,
        addToBackStack: Boolean = true,
        animation: Boolean = true,
        bundle: Bundle? = null
    )

    fun setAppBarTitle(title: String)

    fun onBackPressed(bundle: Bundle? = null)

    fun showActivity(activity: Class<*>, key: String? = null, bundle: Bundle? = null)

    fun switchTab(@IdRes id: Int)
}