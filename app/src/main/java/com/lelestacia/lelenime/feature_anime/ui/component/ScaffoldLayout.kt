package com.lelestacia.lelenime.feature_anime.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.lelestacia.lelenime.feature_anime.util.AnimeScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AnimeScaffoldLayout(
    modifier: Modifier = Modifier,
    screen: @Composable (PaddingValues) -> Unit,
    navController: NavHostController,
    backgroundColor: Color
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LeleNime", color = Color.White)
                }, backgroundColor = backgroundColor
            )
        },
        modifier = modifier,
        bottomBar = {
            if (navController.currentDestination?.route != AnimeScreen.DetailAnime.route) {
                AnimeBottomBar(navController = navController, backgroundColor = backgroundColor)
            }
            println(navController.currentDestination?.route)
        },
        content = screen,
        scaffoldState = scaffoldState
    )
}