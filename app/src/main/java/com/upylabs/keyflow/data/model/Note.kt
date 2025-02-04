package com.upylabs.keyflow.data.model

enum class Accidental {
    NATURAL, SHARP, FLAT
}

data class Note(
    val name: String,
    val octave: Int,
    val frequency: Float,
    val position: Int,
    val accidental: Accidental = Accidental.NATURAL
)

// Notendefinitionen
object Notes {
    val BEGINNER_NOTES = listOf(
        Note("C", 4, 261.63f, 0, Accidental.NATURAL),
        Note("D", 4, 293.66f, -1, Accidental.NATURAL),
        Note("E", 4, 329.63f, -2, Accidental.NATURAL),
        Note("F", 4, 349.23f, -3, Accidental.NATURAL),
        Note("G", 4, 392.00f, -4, Accidental.NATURAL)
    )

    val INTERMEDIATE_NOTES = BEGINNER_NOTES + listOf(
        Note("A", 4, 440.00f, -5, Accidental.NATURAL),
        Note("B", 4, 493.88f, -6, Accidental.NATURAL),
        Note("C", 5, 523.25f, -7, Accidental.NATURAL)
    )

    val ADVANCED_NOTES = INTERMEDIATE_NOTES + listOf(
        Note("C", 4, 277.18f, 0, Accidental.SHARP),
        Note("D", 4, 277.18f, -1, Accidental.FLAT),
        Note("E", 4, 311.13f, -2, Accidental.FLAT),
        Note("F", 4, 369.99f, -3, Accidental.SHARP),
        Note("G", 4, 415.30f, -4, Accidental.SHARP)
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