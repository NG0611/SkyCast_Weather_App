package com.example.skycastweatherapp.network

import com.example.skycastweatherapp.Data.CurrentWeather
import com.example.skycastweatherapp.Data.Forecast
import com.example.skycastweatherapp.Data.ForecastX
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url


private const val base_url="https://api.weatherapi.com/v1/"

private val retrofit= Retrofit.Builder()
    .baseUrl(base_url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface weatherapiservice{
    @GET()
    suspend fun getcurrentweather(@Url endUrl:String): CurrentWeather
    @GET()
    suspend fun getforecastweather(@Url endUrl:String): Forecast
}

object Weatherapi{
    // lazy initialisation
    val retrofitService:weatherapiservice by lazy{
        retrofit.create(weatherapiservice::class.java)
    }

}
