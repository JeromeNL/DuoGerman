package nl.joramkwetters.duogerman.Navigation

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppNavigation(context: Context, dataStoreUtil: DataStoreUtil, themeViewModel: ThemeViewModel, intent: Intent) {
    val navController = rememberNavController()

    // Bepaal de startbestemming op basis van de ontvangen intent
    val startDestination = if (intent.action == Intent.ACTION_SEND && intent.type == "text/plain") {
        Screens.WordsScreen.name // Start op WordsScreen als de intent overeenkomt
    } else {
        Screens.HomeScreen.name // Anders start op HomeScreen
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                listOfNavItems.forEach { navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                        onClick = {
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
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
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screens.HomeScreen.name) {
                HomeScreen()
            }
            composable(route = Screens.WordsScreen.name) {
                WordsScreen(intent) // Zorg ervoor dat je de intent doorgeeft aan WordsScreen
            }
            composable(route = Screens.NewsScreen.name) {
                NewsScreen()
            }
            composable(route = Screens.SettingsScreen.name) {
                SettingsScreen(context, dataStoreUtil, themeViewModel)
            }
        }
    }
}
