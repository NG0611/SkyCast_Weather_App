package com.example.skycastweatherapp.screens

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skycastweatherapp.CustomUI.Background_Image
import com.example.skycastweatherapp.CustomUI.weathersection
import com.example.skycastweatherapp.Data.CurrentWeather
import com.example.skycastweatherapp.network.WeatherUiState
import com.example.skycastweatherapp.network.weather
import com.example.skycastweatherapp.viewmodels.WeatherViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun skycasthomescreen(modifier: Modifier = Modifier,
                      weatherviewmodel:WeatherViewModel,
                      uiState: WeatherUiState)
{
    var cityname by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current



    Box(modifier=Modifier.fillMaxSize())
    {
        Background_Image()
        Column()
        {

            TopAppBar(title = {Text(text = "\uD83C\uDF24 SkyCast",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                fontFamily = FontFamily.Monospace,
                color = Color.White)},

                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row(modifier=Modifier.padding(10.dp),

                    verticalAlignment = Alignment.CenterVertically)
                {
                    OutlinedTextField(value=cityname,
                        onValueChange = {
                            cityname=it },
                        label={Text(text = "Enter Location")},
                        maxLines = 2,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        singleLine = true,
                        modifier = Modifier.weight(0.8f),
                        shape = RoundedCornerShape(30.dp),
                        colors= OutlinedTextFieldDefaults.colors(Color.White,
                            unfocusedBorderColor = Color.White,
                            focusedBorderColor = Color.White,
                            cursorColor = Color.White,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.White,
                            unfocusedTextColor = Color.White)

                    )

                    IconButton(onClick = {

                        focusManager.clearFocus()
                        weatherviewmodel.set_location(cityname)
                        weatherviewmodel.getWeatherData()
                         },
                        modifier=Modifier.weight(0.2f).size(120.dp)){
                        Icon(imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.White)
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Column (modifier= Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally)
                {
                    if(uiState==WeatherUiState.Loading)
                    {
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(20.dp))
                        {
                            Text(text = "Loading...Please Wait!",
                                fontSize=28.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold)
                            Spacer(modifier= Modifier.height(20.dp))
                            Text(text = "⏳",
                                fontSize=72.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold)



                        }

                    }
                    else if(uiState==WeatherUiState.Error)
                    {
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(20.dp))
                        {
                            Text(text = "\uD83D\uDEAB",
                                fontSize=72.sp,

                                fontWeight = FontWeight.Bold)
                            Text(text = "Error Fetching Data",
                                fontSize=22.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold)
                            Text(text = "Check Your Network Connection Or",
                                fontSize=22.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold)
                            Text(text = "Enter Valid Location",
                                fontSize=22.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold)

                        }
                    }
                    else if(uiState is WeatherUiState.Success)
                    {
                        weathersection(weather=uiState.weather)

                    }

                }



            }


        }







    }

}

@Preview(showBackground = true)
@Composable
fun skycasthomescreenpreview() {
    val weatherviewmodel: WeatherViewModel= viewModel()
    weatherviewmodel.getWeatherData()
    skycasthomescreen(weatherviewmodel=weatherviewmodel, uiState=weatherviewmodel.uistate)

}


