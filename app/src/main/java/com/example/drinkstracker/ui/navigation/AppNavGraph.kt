package com.example.drinkstracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.drinkstracker.ui.screen.demo.DemoScreen
import com.example.drinkstracker.ui.screen.home.HomeScreen
import com.example.drinkstracker.ui.screen.home.HomeViewModel
import com.example.drinkstracker.ui.screen.language.LanguageScreen
import com.example.drinkstracker.ui.screen.language.LanguageViewModel


@Composable
fun AppNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.LANGUAGE
    ) {
        composable(NavRoutes.LANGUAGE) {
            LanguageScreen(
                onLanguageChosen = {
                    navController.navigate(NavRoutes.DEMO) {
                        popUpTo(NavRoutes.LANGUAGE) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(NavRoutes.DEMO) { DemoScreen(
            onFinished = {
                navController.navigate(NavRoutes.HOME) {
                    popUpTo(0)
                    launchSingleTop = true
                }
            }
        ) }
        composable(NavRoutes.HOME) { HomeScreen() }
    }
}