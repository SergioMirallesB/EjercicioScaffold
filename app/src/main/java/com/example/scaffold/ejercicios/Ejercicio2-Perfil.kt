package com.example.scaffold.ejercicios

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ejercicio2PerfilUsuario() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var avatarUrl by remember { mutableStateOf("https://i.pravatar.cc/300?img=1") }
    var nombre by remember { mutableStateOf("Sergio") }
    var correo by remember { mutableStateOf("sergio@gmail.com") }

    var recibirEmails by remember { mutableStateOf(true) }
    var modoOscuro by remember { mutableStateOf(false) }

    var currentSection by remember { mutableStateOf("Perfil") }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(title = { Text("Mi perfil") })
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.AccountCircle, null) },
                    label = { Text("Perfil") },
                    selected = currentSection == "Perfil",
                    onClick = { currentSection = "Perfil" }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Notifications, null) },
                    label = { Text("Notificaciones") },
                    selected = currentSection == "Notificaciones",
                    onClick = { currentSection = "Notificaciones" }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, null) },
                    label = { Text("Ajustes") },
                    selected = currentSection == "Ajustes",
                    onClick = { currentSection = "Ajustes" }
                )
            }
        }
    ) { padding ->
        // Contenedor principal que centra el contenido
        Box(modifier = Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) {

            // Lógica para cambiar de pantalla
            when (currentSection) {
                "Perfil" -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        AsyncImage(
                            model = avatarUrl,
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .size(150.dp)
                                .clip(CircleShape)
                        )
                        Text(nombre, style = MaterialTheme.typography.headlineSmall)
                        Text(correo, style = MaterialTheme.typography.bodyMedium)

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(onClick = {
                                avatarUrl = "https://i.pravatar.cc/300?img=1"
                                nombre = "Sergio"
                                correo = "sergio@gmail.com"
                                scope.launch { snackbarHostState.showSnackbar("Avatar actualizado a #1") }
                            }) { Text("Avatar 1") }

                            Button(onClick = {
                                avatarUrl = "https://i.pravatar.cc/300?img=5"
                                nombre = "Pepe"
                                correo = "usuario2@gmail.com"
                                scope.launch { snackbarHostState.showSnackbar("Avatar actualizado a #2") }
                            }) { Text("Avatar 2") }

                            Button(onClick = {
                                avatarUrl = "https://i.pravatar.cc/300?img=8"
                                nombre = "Juan"
                                correo = "usuario3@gmail.com"
                                scope.launch { snackbarHostState.showSnackbar("Avatar actualizado a #3") }
                            }) { Text("Avatar 3") }
                        }
                    }
                }
                "Notificaciones" -> {
                    Text("No tienes notificaciones")
                }
                "Ajustes" -> {
                    Column(modifier = Modifier.padding(32.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Recibir emails", modifier = Modifier.weight(1f))
                            Switch(checked = recibirEmails, onCheckedChange = { recibirEmails = it })
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Modo oscuro", modifier = Modifier.weight(1f))
                            Switch(checked = modoOscuro, onCheckedChange = { modoOscuro = it })
                        }
                    }
                }
            }
        }
    }
}