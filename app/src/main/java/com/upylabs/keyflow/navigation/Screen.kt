package com.upylabs.keyflow.navigation

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object BeginnerMode : Screen("beginner")
    object AdvancedMode : Screen("advanced")
    object NoteReading : Screen("note_reading")
    
    // Weitere Screens können hier hinzugefügt werden
    object Profile : Screen("profile")
    object Settings : Screen("settings")
    object Practice : Screen("practice")

    object Beginner : Screen("beginner")
    object Advanced : Screen("advanced")
    object Professional : Screen("professional")
    object NoteReadingLesson : Screen("note_reading_lesson")
} 