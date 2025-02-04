package com.upylabs.keyflow.ui.screens.onboarding

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.upylabs.keyflow.data.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class SkillLevel {
    BEGINNER,
    ADVANCED
}

enum class PracticeFrequency {
    DAILY,
    WEEKLY,
    OCCASIONALLY
}

data class OnboardingState(
    val currentPage: Int = 0,
    val skillLevel: SkillLevel? = null,
    val canReadNotes: Boolean? = null,
    val practiceFrequency: PracticeFrequency? = null
)

class OnboardingViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferencesRepository = UserPreferencesRepository(application)
    
    private val _state = MutableStateFlow(OnboardingState())
    val state: StateFlow<OnboardingState> = _state.asStateFlow()

    fun updateSkillLevel(level: SkillLevel) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                skillLevel = level,
                currentPage = _state.value.currentPage + 1
            )
        }
    }

    fun updateCanReadNotes(canRead: Boolean) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                canReadNotes = canRead,
                currentPage = _state.value.currentPage + 1
            )
        }
    }

    fun updatePracticeFrequency(frequency: PracticeFrequency) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                practiceFrequency = frequency,
                currentPage = _state.value.currentPage + 1
            )
            
            // Speichere die Daten, wenn alle Informationen vorhanden sind
            with(_state.value) {
                if (skillLevel != null && canReadNotes != null && practiceFrequency != null) {
                    userPreferencesRepository.saveOnboardingData(
                        skillLevel = skillLevel,
                        canReadNotes = canReadNotes,
                        practiceFrequency = practiceFrequency
                    )
                }
            }
        }
    }

    fun isOnboardingComplete(): Boolean {
        return with(_state.value) {
            skillLevel != null && canReadNotes != null && practiceFrequency != null
        }
    }
} 