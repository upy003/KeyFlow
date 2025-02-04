package com.upylabs.keyflow.ui.screens.onboarding

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    onComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentPage by remember { mutableStateOf(0) }
    val questions = listOf(
        "Bist du Anfänger oder Fortgeschrittener?",
        "Kannst du Noten lesen?",
        "Wie oft möchtest du üben?"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = questions[currentPage],
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        ElevatedButton(
            onClick = {
                if (currentPage < questions.size - 1) {
                    currentPage++
                } else {
                    onComplete()
                }
            }
        ) {
            Text(
                text = if (currentPage < questions.size - 1) 
                    "Weiter" else "Fertig"
            )
        }
    }
}

@Composable
private fun SkillLevelPage(
    onSkillLevelSelected: (SkillLevel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Bist du Anfänger oder Fortgeschrittener?",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        
        ElevatedButton(
            onClick = { onSkillLevelSelected(SkillLevel.BEGINNER) },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Anfänger")
        }
        
        ElevatedButton(
            onClick = { onSkillLevelSelected(SkillLevel.ADVANCED) },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Fortgeschrittener")
        }
    }
}

@Composable
private fun NotesReadingPage(
    onAnswerSelected: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Kannst du Noten lesen?",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        
        ElevatedButton(
            onClick = { onAnswerSelected(true) },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Ja")
        }
        
        ElevatedButton(
            onClick = { onAnswerSelected(false) },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Nein")
        }
    }
}

@Composable
private fun PracticeFrequencyPage(
    onFrequencySelected: (PracticeFrequency) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Wie oft möchtest du üben?",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        
        ElevatedButton(
            onClick = { onFrequencySelected(PracticeFrequency.DAILY) },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Täglich")
        }
        
        ElevatedButton(
            onClick = { onFrequencySelected(PracticeFrequency.WEEKLY) },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Wöchentlich")
        }
        
        ElevatedButton(
            onClick = { onFrequencySelected(PracticeFrequency.OCCASIONALLY) },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Gelegentlich")
        }
    }
} 