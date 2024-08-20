package com.example.soypuente

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen(onRegisterSuccess: () -> Unit) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)


    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Regístrate", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text(text = "Ingresa tu Correo") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text(text = "Escribir Contraseña") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (emailState.value.isNotEmpty() && passwordState.value.isNotEmpty()) {
                saveCredentials(sharedPreferences, emailState.value, passwordState.value)
                onRegisterSuccess()
            }
        }) {
            Text(text = "Registrarse")
        }
    }
}

private fun saveCredentials(sharedPreferences: SharedPreferences, email: String, password: String) {
    val editor = sharedPreferences.edit()
    editor.putString("email", email)
    editor.putString("password", password)
    editor.apply()
}
