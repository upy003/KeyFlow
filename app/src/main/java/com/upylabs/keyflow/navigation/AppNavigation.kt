package com.upylabs.keyflow.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.upylabs.keyflow.ui.screens.onboarding.OnboardingScreen
import com.upylabs.keyflow.data.model.SkillLevel
import com.upylabs.keyflow.ui.screens.advanced.AdvancedModeScreen
import com.upylabs.keyflow.ui.screens.professional.ProfessionalModeScreen
import com.upylabs.keyflow.ui.screens.beginner.BeginnerModeScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String = "onboarding"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("onboarding") {
            OnboardingScreen(
                onComplete = { state ->
                    // Direkt zum entsprechenden Modus navigieren basierend auf dem Skill Level
                    val destination = when (state.skillLevel) {
                        SkillLevel.BEGINNER -> "beginner"
                        SkillLevel.ADVANCED -> "advanced"
                        SkillLevel.PROFESSIONAL -> "professional"
                        null -> "beginner" // Fallback
                    }
                    // Verhindert, dass der User zurück zum Onboarding navigieren kann
                    navController.navigate(destination) {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }
        
        // Die Modi-Screens
        composable("beginner") {
            BeginnerModeScreen(
                onBackClick = { /* Optional: App beenden oder andere Aktion */ }
            )
        }
        
        composable("advanced") {
            AdvancedModeScreen(
                onBackClick = { /* Optional: App beenden oder andere Aktion */ }
            )
        }
        
        composable("professional") {
            ProfessionalModeScreen(
                onBackClick = { /* Optional: App beenden oder andere Aktion */ }
            )
        }
    }
} 