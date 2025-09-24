package com.example.skycastweatherapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skycastweatherapp.Data.CurrentWeather
import com.example.skycastweatherapp.Data.Forecast
import com.example.skycastweatherapp.Data.ForecastX
import com.example.skycastweatherapp.network.WeatherRepository
import com.example.skycastweatherapp.network.WeatherUiState
import com.example.skycastweatherapp.network.weather
import com.example.skycastweatherapp.network.weatherRepositoryImpl
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    private val weatherRepository: WeatherRepository = weatherRepositoryImpl()
    var uistate: WeatherUiState by mutableStateOf(WeatherUiState.Loading)

    private val exceptionHandler= CoroutineExceptionHandler{ _, _ ->
        uistate=WeatherUiState.Error

    }
    var City by mutableStateOf("New Delhi")

    fun getWeatherData()
    {
        uistate=WeatherUiState.Loading
        viewModelScope.launch(exceptionHandler){
            try
            {
                val currentWeather=async { getcurrentweather() }.await()
                val forecastWeather=async{getforecastweather()}.await()
                uistate=WeatherUiState.Success(weather(currentWeather,forecastWeather))
            }
           catch (e:Exception)
            {
               uistate= WeatherUiState.Error

            }

        }
    }
    fun set_location(city:String){
         City=city

    }
    private suspend fun getcurrentweather(): CurrentWeather{

        val endurl="current.json?key=eb39d9ba89904d339f1102723252009&q=$City"
        return weatherRepository.getcurrentweather(endurl)

    }
    private suspend fun getforecastweather(): Forecast {
        val endurl="forecast.json?key=eb39d9ba89904d339f1102723252009&q=$City&days=7"
        return weatherRepository.getforecastweather(endurl)
    }
}