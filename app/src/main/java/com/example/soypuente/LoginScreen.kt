package com.example.soypuente

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, onRegisterClick: () -> Unit) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logosp),
            contentDescription = "Login image",
            modifier = Modifier.size(200.dp)
        )

        Text(text = "Bienvenido", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Ingresa tu Correo") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Escribir Contraseña") },
            visualTransformation = PasswordVisualTransformation() // Hacer que la contraseña se oculte
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (authenticate(sharedPreferences, email, password)) {
                onLoginSuccess()
            } else {
                // Manejar error de autenticación (mostrar mensaje, etc.)
            }
        }) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(32.dp))

        TextButton(onClick = { onRegisterClick() }) {
            Text(text = "¿No tienes una cuenta? Regístrate")
        }
    }
}

// Función para autenticar al usuario
private fun authenticate(sharedPreferences: SharedPreferences, email: String, password: String): Boolean {
    val savedEmail = sharedPreferences.getString("email", "")
    val savedPassword = sharedPreferences.getString("password", "")
    return email == savedEmail && password == savedPassword
}
