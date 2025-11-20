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
fun EditView(navController: NavController, studentId: Int, viewModel: StudentViewModel) {
    val studentList by viewModel.studentList.collectAsState()
    val student = studentList.find { it.id == studentId }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Editar Estudiante") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
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
        student?.let {
            ContentEditView(
                paddingValues = paddingValues,
                navController = navController,
                viewModel = viewModel,
                student = it
            )
        } ?: Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Estudiante no encontrado")
        }
    }
}

@Composable
fun ContentEditView(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: StudentViewModel,
    student: com.nadiaguerra.scores_unidad3.data.Student
) {
    var name by remember { mutableStateOf(student.name) }
    var lastname by remember { mutableStateOf(student.lastname) }
    var group by remember { mutableStateOf(student.group.toString()) }
    var score by remember { mutableStateOf(student.score.toString()) }
    var mensajeGuardado by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Edita el nombre",
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
            text = "Edita el apellido",
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
            text = "Edita el grupo (A-Z)",
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
            text = "Edita la calificación",
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
                    val scoreInt = score.toIntOrNull() ?: 0
                    val groupChar = group.firstOrNull() ?: 'A'

                    viewModel.editStudent(
                        studentId = student.id,
                        newName = name,
                        newLastName = lastname,
                        newGroup = groupChar,
                        newScore = scoreInt
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
            Text("Guardar Cambios")
        }

        if (mensajeGuardado) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Cambios guardados",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
