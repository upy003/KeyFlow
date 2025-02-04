package com.upylabs.keyflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.upylabs.keyflow.data.UserPreferencesRepository
import com.upylabs.keyflow.navigation.KeyFlowNavGraph
import com.upylabs.keyflow.navigation.Screen
import com.upylabs.keyflow.ui.theme.KeyFlowTheme

class MainActivity : ComponentActivity() {
    private lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        userPreferencesRepository = UserPreferencesRepository(applicationContext)

        setContent {
            KeyFlowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val hasCompletedOnboarding by userPreferencesRepository
                        .hasCompletedOnboarding
                        .collectAsState(initial = false)
                    
                    KeyFlowNavGraph(
                        navController = navController,
                        startDestination = if (hasCompletedOnboarding) {
                            Screen.Home.route
                        } else {
                            Screen.Onboarding.route
                        }
                    )
                }
            }
        }
    }
}