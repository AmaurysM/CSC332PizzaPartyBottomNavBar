package edu.farmingdale.pizzapartybottomnavbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.farmingdale.pizzapartybottomnavbar.ui.theme.PizzaPartyBottomNavBarTheme
import edu.farmingdale.pizzapartybottomnavbar.view.TopNavigationBar
import edu.farmingdale.pizzapartybottomnavbar.viewmodel.BottomBar
import edu.farmingdale.pizzapartybottomnavbar.viewmodel.ButtonDrawer
import edu.farmingdale.pizzapartybottomnavbar.viewmodel.NavigationGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PizzaPartyBottomNavBarTheme {
                val navController: NavHostController = rememberNavController()
                var buttonsVisible by remember { mutableStateOf(true) }
                var topBarVisible by remember { mutableStateOf(true) }
                val drawerState = rememberDrawerState(DrawerValue.Closed)

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ButtonDrawer(
                            navController = navController,
                            drawerState = drawerState,
                        )
                    }
                ) {
                    Scaffold(
                        topBar = {
                            if(topBarVisible) {
                                TopNavigationBar(
                                    navController = navController,
                                    drawerState = drawerState,
                                    modifier = Modifier,
                                )
                            }
                        },

                        bottomBar = {
                            if (buttonsVisible) {
                                BottomBar(
                                    navController = navController,
                                    state = buttonsVisible,
                                    modifier = Modifier
                                )
                            }
                        }
                    ) { paddingValues ->
                        Box(
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            NavigationGraph(navController = navController) { isVisible ->
                                buttonsVisible = isVisible
                                topBarVisible = isVisible
                            }
                        }
                    }
                }
            }
        }
    }

}
