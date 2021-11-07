package com.example.midterm1.network

import com.example.midterm1.models.Coordinates
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val ADDRESS = "api_key=6f5b4986099441269d48aa7d44224577"

private const val BASE_URL = "https://ipgeolocation.abstractapi.com/v1/?"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GeoCodingApiService{
    @GET(ADDRESS)
     suspend fun getAdress():Coordinates
}
object Api {
    val retrofitService : GeoCodingApiService by lazy {
        retrofit.create(GeoCodingApiService::class.java) }

}