package com.nadiaguerra.scores_unidad3.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nadiaguerra.scores_unidad3.presentation.viewmodels.StudentViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController, viewModel: StudentViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Agregar Estudiante") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        ContentAddView(
            paddingValues = paddingValues,
            navController = navController,
            viewModel = viewModel
        )
    }
}

@Composable
fun ContentAddView(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: StudentViewModel
) {
    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var group by remember { mutableStateOf("") }
    var score by remember { mutableStateOf("") }
    var mensajeGuardado by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope() //pa las corroutines

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ingresa el nombre",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ingresa el apellido",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = lastname,
            onValueChange = { lastname = it },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ingresa el grupo (A-Z)",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = group,
            onValueChange = { if (it.length <= 1) group = it.uppercase() },
            label = { Text("Grupo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ingresa la calificación",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = score,
            onValueChange = { score = it },
            label = { Text("Calificación") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (name.isNotBlank() && lastname.isNotBlank() &&
                    group.isNotBlank() && score.isNotBlank()) {
                    //para convertirlo a num entero
                    val scoreInt = score.toIntOrNull() ?: 0
                    val groupChar = group.firstOrNull() ?: 'A' //valor por defecto no quise agarrar errores dhakka

                    viewModel.createStudent(
                        name = name,
                        lastname = lastname,
                        group = groupChar,
                        score = scoreInt
                    )
                    mensajeGuardado = true

                    scope.launch {
                        delay(1000)
                        navController.popBackStack()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }

        if (mensajeGuardado) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Estudiante guardado",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
