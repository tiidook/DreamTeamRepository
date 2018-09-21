package com.androidacademy.hackathonapp.data.repositories.api

import me.codezfox.extension.asyncR
import retrofit2.Call

interface ApiServer {

    fun login(name: String, deviceId: String, firebaseDeviceId: String? = null): Call<Unit>
}

class ApiRepository {

    private val api = Retrofit.build(ApiServer::class.java)

    fun login(name: String, deviceId: String, firebaseDeviceId: String? = null) = asyncR {
        api.login(name, deviceId, firebaseDeviceId).execute()
    }

}