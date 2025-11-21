package com.nadiaguerra.scores_unidad3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nadiaguerra.scores_unidad3.presentation.navigation.StudentNavManager
import com.nadiaguerra.scores_unidad3.ui.theme.Scores_unidad3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scores_unidad3Theme {
                StudentNavManager()
            }
        }
    }
}
