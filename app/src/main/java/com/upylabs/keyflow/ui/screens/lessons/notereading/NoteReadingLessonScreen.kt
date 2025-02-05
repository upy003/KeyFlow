package com.upylabs.keyflow.ui.screens.lessons.notereading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.upylabs.keyflow.ui.components.KeyFlowButton
import com.upylabs.keyflow.ui.components.KeyFlowCard
import com.upylabs.keyflow.ui.theme.BackgroundGradientStart
import com.upylabs.keyflow.ui.theme.BackgroundGradientEnd
import com.upylabs.keyflow.data.model.Feedback

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteReadingLessonScreen(
    onBackClick: () -> Unit,
    viewModel: NoteReadingViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val state by viewModel.state.collectAsState()

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
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = { Text("Notenlesen lernen") },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Zurück")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Score Anzeige
                KeyFlowCard {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Punkte: ${state.score}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Serie: ${state.streak}",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                // Noten Anzeige
                KeyFlowCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        state.currentNote?.let { note ->
                            Text(
                                text = note.name,
                                style = MaterialTheme.typography.displayLarge
                            )
                        }
                    }
                }

                // Antwort Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    listOf("C", "D", "E", "F", "G", "A", "B").forEach { note ->
                        KeyFlowButton(
                            onClick = { viewModel.checkAnswer(note) },
                            modifier = Modifier.weight(1f).padding(4.dp)
                        ) {
                            Text(note)
                        }
                    }
                }

                // Feedback Anzeige
                state.feedback?.let { feedback ->
                    Text(
                        text = when (feedback) {
                            is Feedback.Correct -> "Richtig!"
                            is Feedback.Incorrect -> "Falsch! Es war ${feedback.expected.name}"
                        },
                        style = MaterialTheme.typography.titleMedium,
                        color = when (feedback) {
                            Feedback.Correct -> MaterialTheme.colorScheme.primary
                            is Feedback.Incorrect -> MaterialTheme.colorScheme.error
                        }
                    )
                }
            }
        }
    }
} 