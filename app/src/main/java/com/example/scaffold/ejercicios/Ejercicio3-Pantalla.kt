package com.example.scaffold.ejercicios

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ejercicio3DetalleProducto() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var activeNavItem by remember { mutableStateOf("Inicio") }

    val imgMainUrl1 = "https://cdn.pixabay.com/photo/2015/01/20/13/13/samsung-605439_960_720.jpg"
    val imgMainUrl2 = "https://cdn.pixabay.com/photo/2015/01/20/12/51/mobile-605422_960_720.jpg"
    val imgMainUrl3 = "https://cdn.pixabay.com/photo/2015/01/29/11/36/mobile-616012_960_720.jpg"

    var currentMainImage by remember { mutableStateOf(imgMainUrl1) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Auriculares Pro X") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch { snackbarHostState.showSnackbar("Volver no implementado") }
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Inicio") },
                    selected = activeNavItem == "Inicio",
                    onClick = { activeNavItem = "Inicio" }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, null) },
                    label = { Text("Catálogo") },
                    selected = activeNavItem == "Catálogo",
                    onClick = { activeNavItem = "Catálogo" }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.ShoppingCart, null) },
                    label = { Text("Carrito") },
                    selected = activeNavItem == "Carrito",
                    onClick = { activeNavItem = "Carrito" }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                AsyncImage(
                    model = currentMainImage,
                    contentDescription = "Imagen producto",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                val thumbnails = listOf(imgMainUrl1, imgMainUrl2, imgMainUrl3)
                thumbnails.forEach { imgUrl ->
                    AsyncImage(
                        model = imgUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .border(
                                width = if (currentMainImage == imgUrl) 3.dp else 1.dp,
                                color = if (currentMainImage == imgUrl) MaterialTheme.colorScheme.primary else Color.Gray,
                                shape = MaterialTheme.shapes.small
                            )
                            .clickable { currentMainImage = imgUrl }
                    )
                }
            }

            HorizontalDivider()

            Text("Auriculares Pro X - Edición Limitada", style = MaterialTheme.typography.headlineSmall)
            Text("9,99€", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Text(
                "Cancelación de ruido activa, audio 8d estereo. Perfectos para estudio y viajes.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(
                    onClick = { scope.launch { snackbarHostState.showSnackbar("Producto añadido al carrito") } },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Añadir al carrito")
                }
                Button(
                    onClick = { scope.launch { snackbarHostState.showSnackbar("Compra rápida no disponible") } },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Comprar ahora")
                }
            }

            Text(
                text = "Sección activa: $activeNavItem",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}