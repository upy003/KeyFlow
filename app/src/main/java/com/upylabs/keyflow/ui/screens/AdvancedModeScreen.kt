package com.upylabs.keyflow.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedModeScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Fortgeschrittener Modus") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Zurück")
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
            SongCard(
                title = "Für Elise",
                composer = "Ludwig van Beethoven",
                difficulty = "Mittel",
                onClick = { /* TODO */ }
            )
            
            SongCard(
                title = "Mondscheinsonate",
                composer = "Ludwig van Beethoven",
                difficulty = "Schwer",
                onClick = { /* TODO */ }
            )
            
            SongCard(
                title = "Türkischer Marsch",
                composer = "Wolfgang Amadeus Mozart",
                difficulty = "Mittel",
                onClick = { /* TODO */ }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SongCard(
    title: String,
    composer: String,
    difficulty: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "von $composer",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Schwierigkeit: $difficulty",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
} 