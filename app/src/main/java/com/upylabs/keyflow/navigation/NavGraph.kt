package com.upylabs.keyflow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.upylabs.keyflow.ui.screens.onboarding.OnboardingScreen
import com.upylabs.keyflow.ui.screens.HomeScreen
import com.upylabs.keyflow.ui.screens.beginner.BeginnerModeScreen
import com.upylabs.keyflow.ui.screens.advanced.AdvancedModeScreen

@Composable
fun KeyFlowNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Onboarding.route
    ) {
        composable(route = Screen.Onboarding.route) {
            OnboardingScreen(
                onComplete = { navController.navigate(Screen.Home.route) }
            )
        }
        
        composable(route = Screen.Home.route) {
            HomeScreen(
                onBeginnerModeClick = { navController.navigate(Screen.BeginnerMode.route) },
                onAdvancedModeClick = { navController.navigate(Screen.AdvancedMode.route) }
            )
        }
        
        composable(route = Screen.BeginnerMode.route) {
            BeginnerModeScreen(
                onBackClick = { navController.navigateUp() }
            )
        }
        
        composable(route = Screen.AdvancedMode.route) {
            AdvancedModeScreen(
                onBackClick = { navController.navigateUp() }
            )
        }
    }
} 