package com.example.drinkstracker.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private const val DS_NAME = "app_settings"
val Context.dataStore by preferencesDataStore(DS_NAME)

object PrefsKeys {
    val LANGUAGE_CODE = stringPreferencesKey("language_code")
    val LANGUAGE_SELECTED = booleanPreferencesKey("language_selected")
}

class LanguagePreference(private val context: Context) {
    private val ds = context.dataStore

    val languageCode: Flow<String> =
        ds.data.map { prefs -> prefs[PrefsKeys.LANGUAGE_CODE] ?: "en" }

    val languageSelected: Flow<Boolean> =
        ds.data.map { prefs -> prefs[PrefsKeys.LANGUAGE_SELECTED] ?: false }

    suspend fun setLanguage(code: String) {
        ds.edit { prefs ->
            prefs[PrefsKeys.LANGUAGE_CODE] = code
            prefs[PrefsKeys.LANGUAGE_SELECTED] = true
        }
    }
}