package com.nadiaguerra.scores_unidad3.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(val title: String, val icon: ImageVector)

@Composable
fun NavTab(){
    val tabs = listOf(
        TabItem("Alumnos", Icons.Default.Home),
        TabItem("Estadísticas", Icons.Default.AccountBox)
    )

    var selectedTabIndex by remember{mutableStateOf(0)}

    Scaffold(
       bottomBar = {
           NavigationBar{
               tabs.forEachIndexed { index, tab ->
                   NavigationBarItem(
                       icon = {Icon(tab.icon, contentDescription = tab.title)},
                       label = {Text(tab.title)},
                       selected = selectedTabIndex == index,
                       onClick = {selectedTabIndex = index}
                   )
               }
           }
       }
    ){
        paddingValues ->
        when (selectedTabIndex){
            0 -> StudentNavManager(modifier = Modifier.padding(paddingValues))
            1 -> StatsNavManager(modifier = Modifier.padding(paddingValues))
        }
    }


}