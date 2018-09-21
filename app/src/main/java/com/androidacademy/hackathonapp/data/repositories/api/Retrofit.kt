package com.androidacademy.hackathonapp.data.repositories.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


internal object Retrofit {

    const val URL = "https://hakatonchallenge.herokuapp.com"

    fun <T> build(service: Class<T>, token: Boolean = true, longTimeout: Boolean = false): T {
        return buildRetrofit(token, longTimeout).create(service)
    }

    fun <T> buildWithoutToken(service: Class<T>): T {
        return build(service, false)
    }

    private fun buildRetrofit(token: Boolean, longTimeout: Boolean = false): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpClient(token, longTimeout))
            .build()
    }


    private fun createHttpClient(token: Boolean, longTimeout: Boolean = false): OkHttpClient {
        return OkHttpClient.Builder().also { builder ->
            if (longTimeout) {
                builder.writeTimeout(3, TimeUnit.MINUTES)
            }
            builder.writeTimeout(30, TimeUnit.SECONDS)
            builder.addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()
    }

}