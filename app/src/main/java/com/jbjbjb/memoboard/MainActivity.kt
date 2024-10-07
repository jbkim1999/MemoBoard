package com.jbjbjb.memoboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.jbjbjb.memoboard.ui.theme.MemoBoardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoBoardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MemoBoardScreen()
                }
            }
        }
    }
}
