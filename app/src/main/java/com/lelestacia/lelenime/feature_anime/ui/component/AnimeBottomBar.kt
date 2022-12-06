package com.lelestacia.lelenime.feature_anime.ui.component

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lelestacia.lelenime.feature_anime.util.AnimeNavigationItem
import com.lelestacia.lelenime.feature_anime.util.AnimeScreen

@Composable
fun AnimeBottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    backgroundColor: Color
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val animeNavigationItem = listOf(
        AnimeNavigationItem(
            title = "Dashboard",
            icon = Icons.Filled.Home,
            screen = AnimeScreen.Dashboard
        ),
        AnimeNavigationItem(
            title = "Explore",
            icon = Icons.Filled.Search,
            screen = AnimeScreen.Explore
        ),
        AnimeNavigationItem(
            title = "My List",
            icon = Icons.Filled.List,
            screen = AnimeScreen.MyList
        )
    )
    BottomNavigation(
        modifier = modifier,
        backgroundColor = backgroundColor
    ) {
        animeNavigationItem.map { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.DarkGray,
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}