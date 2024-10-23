package com.example.gmoriapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gmoriapp.ui.theme.GMoriAppTheme
import com.example.gmoriapp.view.GMoriApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GMoriAppTheme {
                GMoriApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    GMoriAppTheme {
        GMoriApp()
    }
}
