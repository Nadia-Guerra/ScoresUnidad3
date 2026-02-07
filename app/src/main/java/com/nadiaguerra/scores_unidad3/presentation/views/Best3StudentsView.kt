package com.nadiaguerra.scores_unidad3.presentation.views

import android.view.TouchDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nadiaguerra.scores_unidad3.data.Student
import com.nadiaguerra.scores_unidad3.presentation.viewmodels.StudentViewModel
import com.nadiaguerra.scores_unidad3.ui.theme.BlueColumn
import com.nadiaguerra.scores_unidad3.ui.theme.Typography


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun StudentListScreen(
    viewModel: StudentViewModel = viewModel()
) {
    val students by viewModel.studentList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mejores Alumnos",
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp),
                        style = MaterialTheme.typography.headlineLarge
                    )
                }

            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(BlueColumn),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
                    .padding(3.dp)
            ) {
//            if (students.isEmpty()) {
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = "No hay estudiantes registrados",
//                        style = MaterialTheme.typography.bodyLarge,
//                        color = Color.Gray
//                    )
//                }
//            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StudentCard()

                }

            }

        }

    }
}

@Composable
fun StudentCard(
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = BlueColumn
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(16.dp)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icons.Default.AccountCircle
            Column {
                Text(
                    text = ("Emilia Gomez"),
//                    text = "${student.name} ${student.lastname}",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                Text(
                    text = ("Grupo A"),
//                    text = "Grupo ${student.group}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 80.dp)
            )
            Text(
                text = ("86"),
//                text = "${student.score}",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        }
    }
}