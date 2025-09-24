package com.example.skycastweatherapp.CustomUI

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skycastweatherapp.network.weather

@Composable
fun weathersection(modifier: Modifier = Modifier,
                   weather: weather)
{
    // current weather section
    val scrollState = rememberScrollState()
    Column (modifier=Modifier.fillMaxSize().verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
    {
        Text(text = "\uD83D\uDCCD "+weather.currentWeather.location.name,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp, color= Color.White)
        Spacer(modifier.padding(5.dp))
        Text(text =weather.currentWeather.location.region+", "+ weather.currentWeather.location.country,
            fontWeight = FontWeight.Light,
            fontSize = 22.sp, color= Color.White,
            modifier=Modifier.padding(20.dp))
        Spacer(modifier.padding(10.dp))
        Text(text = weather.currentWeather.current.temp_c.toInt().toString()+"\u00B0",
            fontWeight=FontWeight.Bold,
            fontSize = 72.sp, color=Color.White)
        Text(" Feels like "+ weather.currentWeather.current.feelslike_c.toInt().toString()+"\u00B0",
            fontSize = 16.sp, color= Color.White)
        Spacer(modifier.padding(15.dp))
        Text(text=weather.currentWeather.current.condition.text,
            fontSize = 22.sp, color= Color.White,
            fontWeight = FontWeight.Light,
            modifier=Modifier.padding(10.dp))
        Spacer(modifier.padding(10.dp))
        // 4 place holders
        FourPlaceholderBoxes(weather=weather)





        // Forecast weather section
        Spacer(Modifier.height(20.dp))
        LazyRow (modifier=Modifier)
        {items(items=weather.forecastweather.forecast.forecastday){ it->
            SimpleCard(date=it.date,
                day="☔ Rain% "+it.day.daily_chance_of_rain.toString(),
                temperature="\uD83C\uDF21\uFE0F"+it.day.avgtemp_c.toInt().toString()+"\u00B0",
                weatherCondition=it.day.condition.text,
                min_temp=it.day.mintemp_c.toInt().toString()+"\u00B0",
                max_temp=it.day.maxtemp_c.toInt().toString()+"\u00B0")


        }

        }


    }






}

@Composable
fun FourPlaceholderBoxes(
    modifier: Modifier = Modifier,
    boxAlpha: Float = 0.6f,         // change opacity here (0f..1f)
    cornerRadius: Int = 30,          // rounded corner dp
    boxSpacing: Int = 12 ,
    weather: weather
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(boxSpacing.dp)
    ) {
        // First row (2 boxes)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(boxSpacing.dp)) {
            PlaceholderBox(modifier = Modifier.weight(1f),
                alpha = boxAlpha,
                cornerRadius = cornerRadius,
                text1= "Wind Direction",
                text2=weather.currentWeather.current.wind_dir
            )

            PlaceholderBox(modifier = Modifier.weight(1f),
                alpha = boxAlpha,
                cornerRadius = cornerRadius,
                text1="Wind Speed",
                text2 = weather.currentWeather.current.wind_kph.toInt().toString()+"km/h")
        }

        Spacer(modifier = Modifier.height(boxSpacing.dp))

        // Second row (2 boxes)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(boxSpacing.dp)) {
            PlaceholderBox(modifier = Modifier.weight(1f),
                alpha = boxAlpha,
                cornerRadius = cornerRadius,
                text1="Visibility",
                text2=weather.currentWeather.current.vis_miles.toInt().toString()+" miles")
            PlaceholderBox(modifier = Modifier.weight(1f),
                alpha = boxAlpha,
                cornerRadius = cornerRadius,
                text1="Humidity",
                text2 = weather.currentWeather.current.humidity.toString()+"%")

        }
    }
}

@Composable
private fun PlaceholderBox(
    modifier: Modifier = Modifier,
    alpha: Float = 0.6f,
    cornerRadius: Int = 30,
    text1:String,
    text2:String
) {
    // Each box keeps a square-ish shape using aspectRatio
    Box(
        modifier = modifier
            .aspectRatio(1f) // makes box square-like; remove if you want flexible height
            .background(
                color = Color.White.copy(alpha = alpha),
                shape = RoundedCornerShape(cornerRadius.dp)
            )
            .padding(12.dp), // inner padding for actual text/content
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(
                text = text1,
                color = Color.Black,
                fontSize = 22.sp
            )
            Text(
                text=text2,
                color = Color.Black,
                fontSize = 22.sp

            )

        }


    }
}

@Composable
fun SimpleCard(
    date: String,
    day: String,
    temperature: String,
    weatherCondition: String,
    min_temp:String,
    max_temp:String
) {
    Card(
        modifier = Modifier
                    // vertical rectangle
            .padding(8.dp),        // card ke around spacing
        colors = CardDefaults.cardColors(Color.White.copy(alpha = 0.6f)), // white with opacity
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),         // shadow
        shape = RoundedCornerShape(20.dp) // corners round
    ) {
        // Card ke andar content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end=20.dp),    // andar padding

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = date,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = day,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = temperature,
                fontSize = 62.sp,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = weatherCondition,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Min Temp: $min_temp",
                fontSize = 16.sp)
            Text(text = "Max Temp: $max_temp",
                fontSize = 16.sp)

        }
    }
}


