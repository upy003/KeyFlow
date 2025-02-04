package com.upylabs.keyflow.ui.screens.lessons.notereading

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.upylabs.keyflow.data.model.Accidental
import com.upylabs.keyflow.data.model.Note
import com.upylabs.keyflow.data.model.Notes
import com.upylabs.keyflow.data.model.NoteLevel
import com.upylabs.keyflow.audio.NoteAudioService
import com.upylabs.keyflow.data.model.Feedback
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

data class NoteReadingState(
    val currentNote: Note? = null,
    val score: Int = 0,
    val streak: Int = 0,
    val feedback: Feedback? = null,
    val isGameOver: Boolean = false,
    val currentLevel: NoteLevel = NoteLevel.BEGINNER,
    val isPlaying: Boolean = false,
    val isNoteAnimating: Boolean = false
)

class NoteReadingViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val audioService = NoteAudioService(application)
    
    private val _state = MutableStateFlow(NoteReadingState())
    val state: StateFlow<NoteReadingState> = _state.asStateFlow()

    private fun getNotesForLevel(level: NoteLevel): List<Note> = when (level) {
        NoteLevel.BEGINNER -> Notes.BEGINNER_NOTES
        NoteLevel.INTERMEDIATE -> Notes.INTERMEDIATE_NOTES
        NoteLevel.ADVANCED -> Notes.ADVANCED_NOTES
    }

    fun setLevel(level: NoteLevel) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                currentLevel = level,
                score = 0,
                streak = 0
            )
            generateNewNote()
        }
    }

    private fun generateNewNote() {
        viewModelScope.launch {
            val notes = getNotesForLevel(_state.value.currentLevel)
            val newNote = notes.random()
            
            _state.value = _state.value.copy(
                currentNote = newNote,
                feedback = null,
                isNoteAnimating = true
            )
            
            // Spiele die Note ab
            audioService.playNote(newNote)
            
            // Setze die Animation nach einer Verzögerung zurück
            delay(500)
            _state.value = _state.value.copy(isNoteAnimating = false)
        }
    }

    fun playCurrentNote() {
        _state.value.currentNote?.let { audioService.playNote(it) }
    }

    fun checkAnswer(answer: String) {
        val currentNote = _state.value.currentNote ?: return
        val isCorrect = answer.equals(currentNote.name, ignoreCase = true)

        viewModelScope.launch {
            _state.value = _state.value.copy(
                score = _state.value.score + if (isCorrect) 10 else 0,
                streak = if (isCorrect) _state.value.streak + 1 else 0,
                feedback = if (isCorrect) {
                    Feedback.Correct
                } else {
                    Feedback.Incorrect(currentNote)
                }
            )

            if (isCorrect) {
                generateNewNote()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        audioService.release()
    }
} 