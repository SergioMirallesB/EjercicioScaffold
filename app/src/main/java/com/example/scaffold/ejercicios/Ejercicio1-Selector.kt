package com.example.scaffold.ejercicios

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ejercicio1SelectorTema() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var selectedTab by remember { mutableIntStateOf(1) } // 0: Inicio, 1: Config, 2: Perfil
    var temaSeleccionado by remember { mutableStateOf("Sistema") }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Configuración") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Inicio") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                    label = { Text("Configuración") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("Perfil") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val tabName = when(selectedTab) { 0 -> "Inicio"; 1 -> "Configuración"; else -> "Perfil" }
            Text(text = "Pestaña actual: $tabName", style = MaterialTheme.typography.bodyLarge)

            ThemeCard(
                title = "Tema Claro",
                description = "Colores brillantes y alto contraste.",
                imageUrl = "https://picsum.photos/id/10/300/100",
                onClick = {
                    temaSeleccionado = "Claro"
                    scope.launch { snackbarHostState.showSnackbar("Tema Claro activado") }
                }
            )
            ThemeCard(
                title = "Tema Oscuro",
                description = "Ideal para entornos con poca luz.",
                imageUrl = "https://picsum.photos/id/20/300/100", // Imagen ejemplo objetos oscuros
                onClick = {
                    temaSeleccionado = "Oscuro"
                    scope.launch { snackbarHostState.showSnackbar("Tema Oscuro activado") }
                }
            )
            ThemeCard(
                title = "Sistema",
                description = "Se adapta a la configuración de tu dispositivo.",
                imageUrl = "https://picsum.photos/id/30/300/100", // Imagen ejemplo tecnología
                onClick = {
                    temaSeleccionado = "Sistema"
                    scope.launch { snackbarHostState.showSnackbar("Tema Sistema activado") }
                }
            )
        }
    }
}

@Composable
fun ThemeCard(title: String, description: String, imageUrl: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(100.dp)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Text(text = description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}