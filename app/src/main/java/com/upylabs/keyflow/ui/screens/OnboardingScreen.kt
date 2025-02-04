package com.upylabs.keyflow.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
        
        Button(
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