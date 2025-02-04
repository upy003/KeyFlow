package com.upylabs.keyflow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.upylabs.keyflow.ui.screens.HomeScreen
import com.upylabs.keyflow.ui.screens.onboarding.OnboardingScreen
import com.upylabs.keyflow.ui.screens.beginner.BeginnerModeScreen
import com.upylabs.keyflow.ui.screens.advanced.AdvancedModeScreen
import com.upylabs.keyflow.ui.screens.lessons.notereading.NoteReadingScreen

@Composable
fun KeyFlowNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onComplete = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Home.route) {
            HomeScreen(
                onBeginnerModeClick = { navController.navigate(Screen.BeginnerMode.route) },
                onAdvancedModeClick = { navController.navigate(Screen.AdvancedMode.route) }
            )
        }
        
        composable(Screen.BeginnerMode.route) {
            BeginnerModeScreen(
                onBackClick = { navController.navigateUp() }
            )
        }
        
        composable(Screen.AdvancedMode.route) {
            AdvancedModeScreen(
                onBackClick = { navController.navigateUp() }
            )
        }
        
        composable(Screen.NoteReading.route) {
            NoteReadingScreen(
                onBackClick = { navController.navigateUp() }
            )
        }
    }
} 