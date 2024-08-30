package com.example.mobilneprojekat_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobilneprojekat_1.networking.navigation.Navigation
import com.example.mobilneprojekat_1.core.theme.MobilneProjekat_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobilneProjekat_1Theme {
                Navigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobilneProjekat_1Theme {
        Surface {
            //SearchScreen()
        }
    }
}