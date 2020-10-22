package com.example.luxurykotlin.data.remote

import com.example.luxurykotlin.data.model.RegisterRequest
import io.reactivex.Observable

interface ApiHelper {

    fun doLogin(registerRequest: RegisterRequest): Observable<String>
}