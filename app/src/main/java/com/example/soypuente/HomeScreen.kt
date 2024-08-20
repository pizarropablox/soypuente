package com.example.soypuente

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext

@Composable
fun HomeScreen(onLogout: () -> Unit) {
    // Estado para manejar qué botón está seleccionado
    val selectedButton = remember { mutableStateOf<String?>(null) }

    // Contexto para obtener SharedPreferences
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logosp),
            contentDescription = "Home image",
            modifier = Modifier.size(200.dp)
        )

        Text(text = "Selecciona una opción", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        // Fila con los 3 botones
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botón Uber
            Button(
                onClick = { selectedButton.value = "Uber" },
                modifier = Modifier
                    .background(
                        if (selectedButton.value == "Uber") Color.Gray else Color.Transparent
                    )
            ) {
                Text(text = "Uber")
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Botón WhatsApp
            Button(
                onClick = { selectedButton.value = "WhatsApp" },
                modifier = Modifier
                    .background(
                        if (selectedButton.value == "WhatsApp") Color.Gray else Color.Transparent
                    )
            ) {
                Text(text = "WhatsApp")
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Botón Banco
            Button(
                onClick = { selectedButton.value = "Banco" },
                modifier = Modifier
                    .background(
                        if (selectedButton.value == "Banco") Color.Gray else Color.Transparent
                    )
            ) {
                Text(text = "Banco")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón Iniciar que solo se habilita si se seleccionó un botón
        Button(
            onClick = { /* Acción de iniciar según el botón seleccionado */ },
            enabled = selectedButton.value != null,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Iniciar", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón Cerrar sesión
        TextButton(onClick = {
            // Cerrar sesión y navegar a la pantalla de login
            with(sharedPreferences.edit()) {
                putBoolean("isLoggedIn", false)
                apply()
            }
            onLogout()
        }) {
            Text(text = "Cerrar sesión")
        }
    }
}
