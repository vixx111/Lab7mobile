package com.example.lab7mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab7mobile.ui.screens.CaliberSpecsScreen
import com.example.lab7mobile.ui.screens.ExecutiveStoryScreen
import com.example.lab7mobile.ui.screens.MainAppScreen
import com.example.lab7mobile.ui.screens.WatchShowcaseScreen
import com.example.lab7mobile.ui.theme.Lab7mobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab7mobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainAppScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Screen 21 - Watch Showcase")
@Composable
fun WatchShowcasePreview() {
    Lab7mobileTheme {
        WatchShowcaseScreen()
    }
}

@Preview(showBackground = true, name = "Screen 20 - Caliber Specs")
@Composable
fun CaliberSpecsPreview() {
    Lab7mobileTheme {
        CaliberSpecsScreen()
    }
}

@Preview(showBackground = true, name = "Screen 22 - Executive Story")
@Composable
fun ExecutiveStoryPreview() {
    Lab7mobileTheme {
        ExecutiveStoryScreen()
    }
}
