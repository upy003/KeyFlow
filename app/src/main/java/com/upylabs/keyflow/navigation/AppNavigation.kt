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
import com.upylabs.keyflow.ui.screens.lessons.notereading.NoteReadingLessonScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String = Screen.Onboarding.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onComplete = { state ->
                    val destination = when (state.skillLevel) {
                        SkillLevel.BEGINNER -> Screen.Beginner.route
                        SkillLevel.ADVANCED -> Screen.Advanced.route
                        SkillLevel.PROFESSIONAL -> Screen.Professional.route
                        null -> Screen.Beginner.route
                    }
                    navController.navigate(destination) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Beginner.route) {
            BeginnerModeScreen(
                onBackClick = { navController.navigateUp() },
                onNoteReadingClick = { 
                    navController.navigate(Screen.NoteReadingLesson.route)
                }
            )
        }
        
        composable(Screen.Advanced.route) {
            AdvancedModeScreen(
                onBackClick = { navController.navigateUp() }
            )
        }
        
        composable(Screen.Professional.route) {
            ProfessionalModeScreen(
                onBackClick = { navController.navigateUp() }
            )
        }

        composable(Screen.NoteReadingLesson.route) {
            NoteReadingLessonScreen(
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}