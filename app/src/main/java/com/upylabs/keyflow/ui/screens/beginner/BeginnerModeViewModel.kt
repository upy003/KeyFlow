package com.upylabs.keyflow.ui.screens.beginner

import androidx.lifecycle.ViewModel
import com.upylabs.keyflow.data.model.Lesson
import com.upylabs.keyflow.data.model.LessonType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class BeginnerModeState(
    val lessons: List<Lesson> = listOf(
        Lesson(
            id = "note_reading",
            title = "Notenlesen lernen",
            description = "Lerne die Grundlagen des Notenlesens",
            type = LessonType.NOTE_READING
        ),
        Lesson(
            id = "rhythm",
            title = "Rhythmus Training",
            description = "Verbessere dein Taktgefühl",
            type = LessonType.RHYTHM
        ),
        Lesson(
            id = "chords",
            title = "Erste Akkorde",
            description = "Entdecke einfache Akkorde",
            type = LessonType.CHORDS
        ),
        Lesson(
            id = "scales",
            title = "Tonleitern",
            description = "Lerne die wichtigsten Tonleitern",
            type = LessonType.SCALES
        )
    ),
    val selectedLesson: Lesson? = null
)

class BeginnerModeViewModel : ViewModel() {
    private val _state = MutableStateFlow(BeginnerModeState())
    val state: StateFlow<BeginnerModeState> = _state.asStateFlow()

    fun selectLesson(lesson: Lesson) {
        _state.value = _state.value.copy(selectedLesson = lesson)
    }
} 