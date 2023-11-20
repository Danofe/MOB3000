package com.example.mob3000oblig.Color

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.mob3000oblig.R

@Composable
fun ColorLightMode () {
    val primaryColor = colorResource(id = R.color.PRIMARY_LIGHTOGDARK)
    val textLight = colorResource(id = R.color.TEXTLIGHT)
    val backgroundLight = colorResource(id = R.color.LIGHT_BACKGROUNDD)

}
@Composable
fun ColorDarkMode () {
    val primaryColor = colorResource(id = R.color.PRIMARY_LIGHTOGDARK)
    val textDark = colorResource(id = R.color.TEXTDARK)
    val backgroundDark = colorResource(id = R.color.DARK_BACKGROUND)

}