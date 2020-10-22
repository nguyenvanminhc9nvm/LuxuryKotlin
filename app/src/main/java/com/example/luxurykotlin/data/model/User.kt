package com.example.luxurykotlin.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @Expose @SerializedName("password")
    var password: String,
    @Expose
    @SerializedName("phonenumber")
    val phonenumber: String,
    @Expose
    @SerializedName("address")
    val address: String

)