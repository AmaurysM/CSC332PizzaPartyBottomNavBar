package edu.farmingdale.pizzapartybottomnavbar.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(
    navController: NavHostController,
    modifier: Modifier
    , drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()
    TopAppBar(
        modifier = modifier
        , colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
            containerColor = Color.LightGray
        )
        , title = {
            Text(text = "Pizza Party", color = Color.Black)
        }
        , navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            ) {
                Icon(
                    Icons.Outlined.Menu
                    , tint = Color.Black
                    , contentDescription = "Localized description"
                )
            }
        }
    )
}