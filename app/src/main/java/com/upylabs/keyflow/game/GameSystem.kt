package com.upylabs.keyflow.game

data class UserProgress(
    val level: Int = 1,
    val xp: Int = 0,
    val achievements: List<String> = emptyList()
)

class GameSystem {
    private var userProgress = UserProgress()
    
    fun addXP(points: Int) {
        // XP hinzufügen und Level-Up prüfen
    }
    
    fun checkAchievements() {
        // Prüfen ob neue Achievements freigeschaltet wurden
    }
} 