package com.example.skycastweatherapp.CustomUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.skycastweatherapp.R


@Composable
fun Background_Image(modifier: Modifier = Modifier) {
    
    Image(painter = painterResource(R.drawable.phone_wallpapers_vintage),
        contentDescription = null,
        modifier=Modifier.fillMaxSize().alpha(1f),
         contentScale = ContentScale.Crop)

}
