package com.upylabs.keyflow.ui.screens.lessons.notereading

import com.upylabs.keyflow.data.model.Feedback
import com.upylabs.keyflow.data.model.Note
import com.upylabs.keyflow.data.model.NoteLevel

data class NoteReadingScreenState(
    val currentNote: Note? = null,
    val currentLevel: NoteLevel = NoteLevel.BEGINNER,
    val score: Int = 0,
    val streak: Int = 0,
    val feedback: Feedback? = null,
    val isNoteAnimating: Boolean = false
) 