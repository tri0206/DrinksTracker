package com.example.drinkstracker.ui.screen.language

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.example.drinkstracker.data.local.preferences.LanguagePreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first

import java.util.Locale


data class LangState (
    val code: String = "en",
    val selected: Boolean = false,
    val loaded: Boolean = false
)

class LanguageViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = LanguagePreference(app)

    val state: StateFlow<LangState> = combine(
        repo.languageCode,
        repo.languageSelected
    ) { code, selected ->
        LangState(code = code, selected = selected, loaded = true)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, LangState())

    fun setLanguage(code: String) {
        viewModelScope.launch { repo.setLanguage(code) }
    }
}