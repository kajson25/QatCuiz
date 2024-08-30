package com.example.mobilneprojekat_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mobilneprojekat_1.networking.navigation.Navigation
import com.example.mobilneprojekat_1.core.theme.MobilneProjekat_1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobilneProjekat_1Theme {
                Navigation()
            }
        }
    }
}
