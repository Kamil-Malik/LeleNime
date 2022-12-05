package com.lelestacia.lelenime.feature_anime.ui.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
import com.lelestacia.lelenime.core.ui.theme.Orange
import com.lelestacia.lelenime.core.ui.theme.Purple
import com.lelestacia.lelenime.feature_anime.util.AnimeNavigationItem
import com.lelestacia.lelenime.feature_anime.util.AnimeScreen

@Composable
fun AnimeBottomBar(modifier: Modifier = Modifier, navController: NavHostController) {
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
        backgroundColor =
        if (isSystemInDarkTheme()) {
            Purple
        } else {
            Orange
        }
    ) {
        animeNavigationItem.map { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = Color.White
                    )
                },
                label = {
                    Text(text = item.title, color = Color.White)
                },
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