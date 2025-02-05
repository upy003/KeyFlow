package com.upylabs.keyflow.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeyFlowCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val cardShape = MaterialTheme.shapes.large
    
    Card(
        modifier = modifier
            .clip(cardShape)
            .graphicsLayer {
                shadowElevation = 12f
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = cardShape,
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        )
    ) {
        content()
    }
} 