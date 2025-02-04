package com.upylabs.keyflow.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.upylabs.keyflow.ui.screens.onboarding.PracticeFrequency
import com.upylabs.keyflow.ui.screens.onboarding.SkillLevel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class UserPreferencesRepository(private val context: Context) {
    
    private object PreferencesKeys {
        val SKILL_LEVEL = stringPreferencesKey("skill_level")
        val CAN_READ_NOTES = booleanPreferencesKey("can_read_notes")
        val PRACTICE_FREQUENCY = stringPreferencesKey("practice_frequency")
        val HAS_COMPLETED_ONBOARDING = booleanPreferencesKey("has_completed_onboarding")
    }

    private val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")

    val hasCompletedOnboarding: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[ONBOARDING_COMPLETED] ?: false
        }

    suspend fun completeOnboarding() {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_COMPLETED] = true
        }
    }

    suspend fun saveOnboardingData(
        skillLevel: SkillLevel,
        canReadNotes: Boolean,
        practiceFrequency: PracticeFrequency
    ) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SKILL_LEVEL] = skillLevel.name
            preferences[PreferencesKeys.CAN_READ_NOTES] = canReadNotes
            preferences[PreferencesKeys.PRACTICE_FREQUENCY] = practiceFrequency.name
            preferences[PreferencesKeys.HAS_COMPLETED_ONBOARDING] = true
        }
    }

    val userData: Flow<UserData?> = context.dataStore.data
        .map { preferences ->
            val skillLevel = preferences[PreferencesKeys.SKILL_LEVEL]?.let {
                SkillLevel.valueOf(it)
            }
            val canReadNotes = preferences[PreferencesKeys.CAN_READ_NOTES]
            val practiceFrequency = preferences[PreferencesKeys.PRACTICE_FREQUENCY]?.let {
                PracticeFrequency.valueOf(it)
            }

            if (skillLevel != null && canReadNotes != null && practiceFrequency != null) {
                UserData(skillLevel, canReadNotes, practiceFrequency)
            } else {
                null
            }
        }
}

data class UserData(
    val skillLevel: SkillLevel,
    val canReadNotes: Boolean,
    val practiceFrequency: PracticeFrequency
) 