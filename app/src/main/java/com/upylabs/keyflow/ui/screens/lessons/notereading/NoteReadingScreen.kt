package com.upylabs.keyflow.ui.screens.lessons.notereading

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.upylabs.keyflow.data.model.Note
import com.upylabs.keyflow.data.model.NoteLevel
import com.upylabs.keyflow.data.model.Accidental
import com.upylabs.keyflow.data.model.Feedback
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.Path

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteReadingScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NoteReadingViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    var userInput by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Notenlesen") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Zurück")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.playCurrentNote() }) {
                        Icon(Icons.Default.PlayArrow, contentDescription = "Note abspielen")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Score und Streak
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Punkte: ${state.score}")
                Text("Streak: ${state.streak}")
            }

            // Schwierigkeitsauswahl mit ElevatedFilterChip
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NoteLevel.values().forEach { level ->
                    ElevatedFilterChip(
                        selected = state.currentLevel == level,
                        onClick = { viewModel.setLevel(level) },
                        label = {
                            Text(
                                when (level) {
                                    NoteLevel.BEGINNER -> "Anfänger"
                                    NoteLevel.INTERMEDIATE -> "Fortgeschritten"
                                    NoteLevel.ADVANCED -> "Experte"
                                }
                            )
                        }
                    )
                }
            }

            // Notensystem
            StaffView(
                note = state.currentNote,
                isAnimating = state.isNoteAnimating,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // Eingabefeld
            OutlinedTextField(
                value = userInput,
                onValueChange = { userInput = it },
                label = { Text("Note eingeben (A-G)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Prüfen-Button
            ElevatedButton(
                onClick = {
                    viewModel.checkAnswer(userInput)
                    userInput = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Prüfen")
            }

            // Feedback
            state.feedback?.let { feedback ->
                when (feedback) {
                    is Feedback.Correct -> {
                        Text(
                            "Richtig!",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    is Feedback.Incorrect -> {
                        Text(
                            "Falsch! Die richtige Antwort war ${feedback.expected.name}",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StaffView(
    note: Note?,
    isAnimating: Boolean,
    modifier: Modifier = Modifier
) {
    val animatedScale by animateFloatAsState(
        targetValue = if (isAnimating) 1.2f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Canvas(modifier = modifier) {
        val lineSpacing = size.height / 8
        
        // Zeichne die 5 Notenlinien
        repeat(5) { index ->
            val y = size.height / 2 + (index - 2) * lineSpacing
            drawLine(
                Color.Black,
                Offset(0f, y),
                Offset(size.width, y),
                strokeWidth = 1f
            )
        }

        // Zeichne die Note
        note?.let {
            val noteY = size.height / 2 - it.position * lineSpacing / 2
            val noteX = size.width / 2
            
            // Zeichne die Note mit Animation
            scale(animatedScale, Offset(noteX, noteY)) {
                // Zeichne Vorzeichen
                if (it.accidental != Accidental.NATURAL) {
                    val accidentalX = noteX - lineSpacing * 1.5f
                    when (it.accidental) {
                        Accidental.SHARP -> drawSharp(Offset(accidentalX, noteY), lineSpacing)
                        Accidental.FLAT -> drawFlat(Offset(accidentalX, noteY), lineSpacing)
                        else -> {} // NATURAL wird nicht gezeichnet
                    }
                }

                // Zeichne die Note
                drawCircle(
                    Color.Black,
                    radius = lineSpacing / 2,
                    center = Offset(noteX, noteY),
                    style = Stroke(width = 2f)
                )
            }
        }
    }
}

private fun DrawScope.drawSharp(position: Offset, size: Float) {
    val width = size * 0.8f
    val height = size * 1.2f
    
    // Vertikale Linien
    drawLine(
        Color.Black,
        start = Offset(position.x - width/4, position.y - height/2),
        end = Offset(position.x - width/4, position.y + height/2),
        strokeWidth = 2f
    )
    drawLine(
        Color.Black,
        start = Offset(position.x + width/4, position.y - height/2),
        end = Offset(position.x + width/4, position.y + height/2),
        strokeWidth = 2f
    )
    
    // Horizontale Linien
    drawLine(
        Color.Black,
        start = Offset(position.x - width/2, position.y - height/4),
        end = Offset(position.x + width/2, position.y - height/4),
        strokeWidth = 2f
    )
    drawLine(
        Color.Black,
        start = Offset(position.x - width/2, position.y + height/4),
        end = Offset(position.x + width/2, position.y + height/4),
        strokeWidth = 2f
    )
}

private fun DrawScope.drawFlat(position: Offset, size: Float) {
    val width = size * 0.6f
    val height = size * 1.2f
    
    // Vertikale Linie
    drawLine(
        Color.Black,
        start = Offset(position.x, position.y - height/2),
        end = Offset(position.x, position.y + height/2),
        strokeWidth = 2f
    )
    
    // Gebogene Linie für das b-Symbol
    val path = Path().apply {
        moveTo(position.x, position.y + height/4)
        quadraticBezierTo(
            position.x + width,
            position.y + height/4,
            position.x + width/2,
            position.y + height/2
        )
        quadraticBezierTo(
            position.x,
            position.y + height/3,
            position.x,
            position.y + height/4
        )
    }
    drawPath(path, Color.Black, style = Stroke(width = 2f))
} 