package com.androidacademy.hackathonapp.data.repositories.api

import com.androidacademy.hackathonapp.data.models.Task
import me.codezfox.extension.asyncR
import me.codezfox.extension.bodyOrError
import me.codezfox.extension.isSuccessfulOrError
import retrofit2.Call
import retrofit2.http.POST

interface ApiServer {

    @POST("AppUsers")
    fun login(name: String, deviceId: String, firebaseDeviceId: String? = null): Call<Unit>

    fun loadTasks(): Call<List<Task>>

}

class ApiRepository {

    private val api = Retrofit.build(ApiServer::class.java)

    fun login(name: String, deviceId: String, firebaseDeviceId: String? = null) = asyncR {
        api.login(name, deviceId, firebaseDeviceId).execute().isSuccessfulOrError()
    }

    fun loadTasks() = asyncR {
        api.loadTasks().execute().bodyOrError()
    }

    fun acceptTask(task: Task) {

    }

}