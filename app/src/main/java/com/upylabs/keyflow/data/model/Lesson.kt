package com.upylabs.keyflow.data.model

data class Lesson(
    val id: String,
    val title: String,
    val description: String,
    val type: LessonType,
    val isCompleted: Boolean = false
)

enum class LessonType {
    NOTE_READING,    // Notenlesen
    RHYTHM,          // Rhythmusübungen
    CHORDS,          // Akkorde
    SCALES           // Tonleitern
}

enum class LessonDifficulty {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED
} 