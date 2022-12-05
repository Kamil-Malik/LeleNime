package com.lelestacia.lelenime.core.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lelestacia.lelenime.core.ui.screen.LeleNimeApp
import com.lelestacia.lelenime.core.ui.theme.LeleNimeComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeleNimeComposeTheme {
                LeleNimeApp()
            }
        }
    }
}