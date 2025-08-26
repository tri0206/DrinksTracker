package com.example.drinkstracker.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColors = darkColorScheme(
    primary = Blue200,
    onPrimary = Color.Black,
    secondary = Cyan300,
    onSecondary = Color.Black,
    tertiary = Green300,
    onTertiary = Color.Black,
    background = DarkBackground,
    onBackground = LightText,
    surface = Color(0xFF1E1E1E),
    onSurface = LightText
)

private val LightColors = lightColorScheme(
    primary = Blue500,
    onPrimary = Color.White,
    secondary = Cyan500,
    onSecondary = Color.White,
    tertiary = Green500,
    onTertiary = Color.White,
    background = LightBackground,
    onBackground = DarkText,
    surface = Color.White,
    onSurface = DarkText
)

@Composable
fun DrinksTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}