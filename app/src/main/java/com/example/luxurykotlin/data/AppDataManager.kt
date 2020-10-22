package com.example.luxurykotlin.data

import com.example.luxurykotlin.data.local.PreferenceHelper
import com.example.luxurykotlin.data.model.RegisterRequest
import com.example.luxurykotlin.data.remote.AppApiHelper
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val mPreferenceHelper: PreferenceHelper,
    private val mAppApiHelper: AppApiHelper
)  : DataManager {
    override fun doLogin(registerRequest: RegisterRequest): Observable<String> {
        return mAppApiHelper.doLogin(registerRequest)
    }
}