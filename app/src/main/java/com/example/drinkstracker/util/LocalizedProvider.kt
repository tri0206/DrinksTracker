package com.example.drinkstracker.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun ProvideLocalizedResources(
    langCode: String,
    content: @Composable () -> Unit
) {
    val base = LocalContext.current
    // Mỗi khi langCode đổi → tạo Context mới → stringResource() đọc đúng locale mới
    val localized = remember(langCode, base) { base.withLocale(langCode) }
    CompositionLocalProvider(LocalContext provides localized) {
        content()
    }
}