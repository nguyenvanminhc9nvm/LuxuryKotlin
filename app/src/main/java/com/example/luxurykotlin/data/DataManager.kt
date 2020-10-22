package com.example.luxurykotlin.data

import com.example.luxurykotlin.data.local.PreferenceHelper
import com.example.luxurykotlin.data.remote.ApiHelper

interface DataManager : ApiHelper, PreferenceHelper {
}