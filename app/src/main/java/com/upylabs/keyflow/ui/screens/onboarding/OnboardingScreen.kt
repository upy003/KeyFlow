package com.upylabs.keyflow.ui.screens.onboarding

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.upylabs.keyflow.data.model.OnboardingState
import com.upylabs.keyflow.data.model.PracticeFrequency
import com.upylabs.keyflow.data.model.SkillLevel
import com.upylabs.keyflow.ui.components.KeyFlowButton
import com.upylabs.keyflow.ui.components.KeyFlowCard
import androidx.compose.ui.graphics.Brush
import com.upylabs.keyflow.ui.theme.BackgroundGradientStart
import com.upylabs.keyflow.ui.theme.BackgroundGradientEnd

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    onComplete: (OnboardingState) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        BackgroundGradientStart,
                        BackgroundGradientEnd
                    )
                )
            )
    ) {
        var state by remember { mutableStateOf(OnboardingState()) }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (state.currentQuestion) {
                0 -> SkillLevelQuestion(
                    onAnswer = { skillLevel ->
                        state = state.copy(
                            skillLevel = skillLevel,
                            currentQuestion = if (skillLevel == SkillLevel.PROFESSIONAL) 2 else 1
                        )
                    }
                )
                1 -> NoteReadingQuestion(
                    onAnswer = { canReadNotes ->
                        state = state.copy(
                            canReadNotes = canReadNotes,
                            currentQuestion = 2
                        )
                    }
                )
                2 -> PracticeFrequencyQuestion(
                    onAnswer = { frequency ->
                        state = state.copy(
                            practiceFrequency = frequency,
                            currentQuestion = 3
                        )
                        onComplete(state)
                    }
                )
            }
        }
    }
}

@Composable
private fun SkillLevelQuestion(onAnswer: (SkillLevel) -> Unit) {
    QuestionCard(
        question = "Wie würdest du deine Klavierfähigkeiten einschätzen?",
        answers = listOf(
            "Anfänger" to { onAnswer(SkillLevel.BEGINNER) },
            "Fortgeschrittener" to { onAnswer(SkillLevel.ADVANCED) },
            "Profi" to { onAnswer(SkillLevel.PROFESSIONAL) }
        )
    )
}

@Composable
private fun NoteReadingQuestion(onAnswer: (Boolean) -> Unit) {
    QuestionCard(
        question = "Bist du mit dem Lesen von Musiknoten vertraut?",
        answers = listOf(
            "Ja, ich kann Noten lesen" to { onAnswer(true) },
            "Nein, noch nicht" to { onAnswer(false) }
        )
    )
}

@Composable
private fun PracticeFrequencyQuestion(onAnswer: (PracticeFrequency) -> Unit) {
    QuestionCard(
        question = "Wie oft möchtest du pro Woche üben?",
        answers = listOf(
            "Einmal pro Woche" to { onAnswer(PracticeFrequency.ONCE_PER_WEEK) },
            "2-3 Mal pro Woche" to { onAnswer(PracticeFrequency.TWO_TO_THREE_TIMES) },
            "Täglich" to { onAnswer(PracticeFrequency.DAILY) }
        )
    )
}

@Composable
private fun QuestionCard(
    question: String,
    answers: List<Pair<String, () -> Unit>>,
    modifier: Modifier = Modifier
) {
    KeyFlowCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = question,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            answers.forEach { (answer, onClick) ->
                KeyFlowButton(
                    onClick = onClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = answer,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
} 