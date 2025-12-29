package com.example.cookwhatyouhave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cookwhatyouhave.ui.navigation.AppNavigation
import com.example.cookwhatyouhave.ui.theme.CookWhatYouHaveTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CookWhatYouHaveTheme(darkTheme = false) {
                AppNavigation()
            }
        }
    }
}
