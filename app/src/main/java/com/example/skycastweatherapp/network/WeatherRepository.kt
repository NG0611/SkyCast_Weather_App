package com.example.skycastweatherapp.network

import com.example.skycastweatherapp.Data.CurrentWeather
import com.example.skycastweatherapp.Data.Forecast
import com.example.skycastweatherapp.Data.ForecastX

interface WeatherRepository {
    suspend fun getcurrentweather(endUrl:String): CurrentWeather
    suspend fun getforecastweather(endUrl:String): Forecast

}

class weatherRepositoryImpl:WeatherRepository{
    override suspend fun getcurrentweather(endurl:String): CurrentWeather {
        return Weatherapi.retrofitService.getcurrentweather(endurl)
    }
    override suspend fun getforecastweather(endurl:String):Forecast{
        return Weatherapi.retrofitService.getforecastweather(endurl)
    }
}