package nl.joramkwetters.duogerman.Navigation

import android.content.Context
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import nl.joramkwetters.duogerman.Screens.HomeScreen
import nl.joramkwetters.duogerman.Screens.NewsScreen
import nl.joramkwetters.duogerman.Screens.SettingsScreen
import nl.joramkwetters.duogerman.Screens.WordsScreen
import nl.joramkwetters.duogerman.ViewModels.ThemeViewModel
import nl.joramkwetters.duogerman.data.DataStoreUtil


@Composable
fun AppNavigation(context: Context, dataStoreUtil: DataStoreUtil, themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                listOfNavItems.forEach { navItem ->  
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == navItem.route} == true,
                        onClick = {
                                  navController.navigate(navItem.route){
                                      popUpTo(navController.graph.findStartDestination().id){
                                          saveState = true
                                      }
                                      launchSingleTop = true
                                      restoreState = true
                                  }
                        },
                        icon = {
                               Icon(
                                   imageVector = navItem.icon,
                                   contentDescription = null
                               )
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.HomeScreen.name,
            modifier =  Modifier
                .padding(paddingValues)
        ){
            composable(route = Screens.HomeScreen.name){
                HomeScreen()
            }
            composable(route = Screens.WordsScreen.name){
                WordsScreen()
            }
            composable(route = Screens.NewsScreen.name){
                NewsScreen()
            }
            composable(route = Screens.SettingsScreen.name){
                SettingsScreen(context, dataStoreUtil, themeViewModel)
            }

        }
    }
}