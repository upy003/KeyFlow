package com.upylabs.keyflow.data.model

enum class Accidental {
    NATURAL, SHARP, FLAT
}

enum class NoteLevel {
    BEGINNER, INTERMEDIATE, ADVANCED
}

data class Note(
    val name: String,
    val octave: Int,
    val accidental: Accidental = Accidental.NATURAL,
    val position: Float // Position auf dem Notensystem (0 = mittlere Linie)
)

// Notendefinitionen
object Notes {
    val BEGINNER_NOTES = listOf(
        Note("C", 4, position = 0f),
        Note("D", 4, position = -0.5f),
        Note("E", 4, position = -1f),
        Note("F", 4, position = -1.5f),
        Note("G", 4, position = -2f)
    )

    val INTERMEDIATE_NOTES = BEGINNER_NOTES + listOf(
        Note("A", 4, position = -2.5f),
        Note("B", 4, position = -3f),
        Note("C", 5, position = -3.5f)
    )

    val ADVANCED_NOTES = INTERMEDIATE_NOTES + listOf(
        Note("C", 4, Accidental.SHARP, 0f),
        Note("D", 4, Accidental.FLAT, -0.5f),
        Note("E", 4, Accidental.FLAT, -1f),
        Note("F", 4, Accidental.SHARP, -1.5f),
        Note("G", 4, Accidental.SHARP, -2f)
    )
}

// Hilfsfunktionen für die Notenbezeichnung
fun Note.fullName(): String = when (accidental) {
    Accidental.NATURAL -> "$name$octave"
    Accidental.SHARP -> "$name#$octave"
    Accidental.FLAT -> "$name♭$octave"
}

enum class NoteState {
    ACTIVE,
    ARCHIVED
} 