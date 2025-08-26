package com.example.drinkstracker.util

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.compose.runtime.staticCompositionLocalOf
import java.util.Locale

fun Context.withLocale(langTag: String): Context {
    val locale = Locale.forLanguageTag(langTag)
    Locale.setDefault(locale)
    val config = Configuration(resources.configuration)
    config.setLocale(locale)
    config.setLayoutDirection(locale)
    return createConfigurationContext(config)
}