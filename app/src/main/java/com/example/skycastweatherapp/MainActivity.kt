package com.example.skycastweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.skycastweatherapp.ui.theme.SkyCastWeatherAppTheme
import com.example.skycastweatherapp.viewmodels.WeatherViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skycastweatherapp.screens.skycasthomescreen


// Api key 4dc3d6c0897b44249e3182251251009
// baseurl-  http://api.weatherapi.com/v1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkyCastWeatherAppTheme {

                SkycastApp()



                }
            }
        }
    }

@Composable
fun SkycastApp(modifier: Modifier = Modifier) {

    val weatherviewmodel: WeatherViewModel= viewModel()
    LaunchedEffect(Unit){
        weatherviewmodel.getWeatherData()
    }



    skycasthomescreen(weatherviewmodel=weatherviewmodel, uiState=weatherviewmodel.uistate)


}

