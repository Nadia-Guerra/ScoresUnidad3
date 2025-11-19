package com.nadiaguerra.scores_unidad3.presentation.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nadiaguerra.scores_unidad3.data.Student
import com.nadiaguerra.scores_unidad3.presentation.viewmodels.StudentViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardView(navController: NavController, viewModel: StudentViewModel = viewModel()) {
    val studentList by viewModel.studentList.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Dashboard") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("Add") },
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar estudiante")
            }
        }
    ) { paddingValues ->
        ContentStudentView(
            paddingValues = paddingValues,
            studentList = studentList,
            onDelete = { student ->
                viewModel.deleteStudent(student.id)
            },
            onEdit = { studentId ->
                navController.navigate("Edit/$studentId") //se va a la visra de editar estudiante basado en el id
            }
        )
    }
}


@Composable
fun ContentStudentView(
    paddingValues: PaddingValues,
    studentList: List<Student>,
    onDelete: (Student) -> Unit, //recibe un estudainte y no regresa nada
    onEdit: (Int) -> Unit //recibe el id del estudiante y no regresa nada
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        if (studentList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay estudiantes registrados")
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(
                    items = studentList,
                    key = { it.id }
                ) { student -> //por cada estudiante que haya se hace lo demas
                    val deleteAction = SwipeAction(
                        onSwipe = { onDelete(student) },
                        icon = {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Eliminar",
                                tint = Color.White,
                                modifier = Modifier.padding(16.dp)
                            )
                        },
                        background = Color.Red
                    )

                    SwipeableActionsBox(
                        endActions = listOf(deleteAction) //la accion de deslizar de dercha a izquierda
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .clickable { onEdit(student.id) },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = "Nombre: ${student.name}",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Apellido: ${student.lastname}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Grupo: ${student.group}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Calificación: ${student.score}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
