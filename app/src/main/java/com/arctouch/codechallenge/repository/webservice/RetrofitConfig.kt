package com.arctouch.codechallenge.repository.webservice

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {

    private val client by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(TmdbApi.URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    val tmdbApi: TmdbApi by lazy {
        retrofit.create(TmdbApi::class.java)
    }

}