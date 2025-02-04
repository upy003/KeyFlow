package com.upylabs.keyflow.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onBeginnerModeClick: () -> Unit,
    onAdvancedModeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "KeyFlow",
            style = MaterialTheme.typography.headlineLarge
        )
        
        ElevatedButton(
            onClick = onBeginnerModeClick,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Anfänger Modus")
        }
        
        ElevatedButton(
            onClick = onAdvancedModeClick,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Fortgeschrittenen Modus")
        }
    }
} 