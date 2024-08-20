package com.example.soypuente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.soypuente.ui.theme.SoypuenteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoypuenteTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val currentScreen = remember { mutableStateOf(Screen.Login) }

    when (currentScreen.value) {
        Screen.Login -> LoginScreen(
            onLoginSuccess = { currentScreen.value = Screen.Home },
            onRegisterClick = { currentScreen.value = Screen.Register }
        )
        Screen.Register -> RegisterScreen(
            onRegisterSuccess = { currentScreen.value = Screen.Login }
        )
        Screen.Home -> HomeScreen(
            onLogout = { currentScreen.value = Screen.Login }
        )
    }
}

enum class Screen {
    Login,
    Register,
    Home
}
