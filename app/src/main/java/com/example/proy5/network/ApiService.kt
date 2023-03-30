package com.example.proy5.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


private const val BASE_URL = "https://androiddev5-default-rtdb.firebaseio.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val interceptor = HttpLoggingInterceptor()
private val body = interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
var client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(client)
    .baseUrl(BASE_URL)
    .build()

object ConexionApi {
    val retrofitService : Services by lazy {
        retrofit.create(Services::class.java)
    }
}