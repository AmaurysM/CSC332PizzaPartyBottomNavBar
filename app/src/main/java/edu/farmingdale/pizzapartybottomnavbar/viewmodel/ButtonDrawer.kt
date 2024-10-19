package edu.farmingdale.pizzapartybottomnavbar.viewmodel

import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import edu.farmingdale.pizzapartybottomnavbar.movel.BottomNavigationItems
import kotlinx.coroutines.launch

@Composable
fun ButtonDrawer(
    navController: NavHostController,
    drawerState: DrawerState,
){
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val items = listOf(
        BottomNavigationItems.PizzaScreen,
        BottomNavigationItems.GpaAppScreen,
        BottomNavigationItems.Screen3
    )
    ModalDrawerSheet {
        items.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = { Text(text = item.title!!) },
                selected = index == selectedItemIndex,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                    selectedItemIndex = index
                    scope.launch { drawerState.close() }
                },
                icon = {
                    item.icon?.let { icon ->
                        Icon(
                            imageVector = icon, contentDescription = icon.name
                        )
                    }
                },
            )
        }
    }
}