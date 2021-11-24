package com.vaqueiro.aplicacionnotas.data.remote

import android.provider.SyncStateContract
import com.google.gson.GsonBuilder
import com.vaqueiro.aplicacionnotas.app.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val service by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ApiService::class.java)
    }

}