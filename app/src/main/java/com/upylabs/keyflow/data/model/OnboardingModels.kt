package com.upylabs.keyflow.data.model

data class OnboardingState(
    val currentQuestion: Int = 0,
    val skillLevel: SkillLevel? = null,
    val canReadNotes: Boolean? = null,
    val practiceFrequency: PracticeFrequency? = null
)

enum class SkillLevel {
    BEGINNER,
    ADVANCED,
    PROFESSIONAL
}

enum class PracticeFrequency {
    ONCE_PER_WEEK,
    TWO_TO_THREE_TIMES,
    DAILY
} 