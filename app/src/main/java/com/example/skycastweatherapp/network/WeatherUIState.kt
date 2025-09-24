package com.example.skycastweatherapp.network

import com.example.skycastweatherapp.Data.CurrentWeather
import com.example.skycastweatherapp.Data.Forecast
import com.example.skycastweatherapp.Data.ForecastX

data class weather(
    val currentWeather: CurrentWeather,
    val forecastweather: Forecast
)


sealed interface WeatherUiState{
    data class Success(val weather:weather): WeatherUiState
    data object Error: WeatherUiState
    data object Loading: WeatherUiState
}