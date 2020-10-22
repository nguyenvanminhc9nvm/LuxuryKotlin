package com.example.luxurykotlin.data.remote

import com.example.luxurykotlin.data.model.RegisterRequest
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppApiHelper @Inject constructor() : ApiHelper{
    override fun doLogin(registerRequest: RegisterRequest): Observable<String> {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SIGN_UP)
            .addBodyParameter(registerRequest)
            .build()
            .stringObservable
    }
}