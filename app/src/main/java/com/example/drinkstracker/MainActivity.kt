package com.example.drinkstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.drinkstracker.data.local.preferences.LanguagePreference
import com.example.drinkstracker.ui.navigation.AppNavGraph
import com.example.drinkstracker.ui.navigation.NavRoutes
import com.example.drinkstracker.ui.screen.home.HomeScreen
import com.example.drinkstracker.ui.screen.language.LanguageScreen
import com.example.drinkstracker.ui.screen.language.LanguageViewModel
import com.example.drinkstracker.ui.theme.DrinksTrackerTheme
import com.example.drinkstracker.util.ProvideLocalizedResources
import kotlinx.coroutines.launch
import java.util.Locale

val LocalAppLocale = androidx.compose.runtime.staticCompositionLocalOf { Locale.getDefault() }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val vm: LanguageViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
            val langState by vm.state.collectAsStateWithLifecycle()

            // Splash nhẹ trong lúc DataStore load
            if (!langState.loaded) {
                Splash()
                return@setContent
            }

            // Bọc toàn bộ UI bằng Context theo locale hiện tại
            ProvideLocalizedResources(langState.code) {
                val nav = rememberNavController()
                LaunchedEffect(langState.selected) {
                    if (langState.selected) {
                        if (nav.currentDestination?.route != NavRoutes.DEMO) {
                            nav.navigate(NavRoutes.DEMO) {
                                popUpTo(0)
                                launchSingleTop = true
                            }
                        }
                    }
                }

                AppNavGraph(navController = nav)
            }
        }
    }
}

@Composable
fun Splash() {
    androidx.compose.material3.Surface {
        androidx.compose.material3.Text(
            text = "Loading...",
            modifier = androidx.compose.ui.Modifier
                .padding(24.dp)
        )
    }
}
