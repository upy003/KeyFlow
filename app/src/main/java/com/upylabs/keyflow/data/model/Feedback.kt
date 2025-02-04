package com.upylabs.keyflow.data.model

sealed interface Feedback {
    object Correct : Feedback
    data class Incorrect(val expected: Note) : Feedback
} 