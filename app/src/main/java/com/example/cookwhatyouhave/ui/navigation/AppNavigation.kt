package com.example.cookwhatyouhave.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cookwhatyouhave.ui.main.screen.MainScreen

enum class ScreenNavigation(val route:String, val title: String, val icon: ImageVector) {
    Main("main","Main",Icons.Filled.Home),
    Favorites("favorites","Favorites",Icons.Filled.Favorite)
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            MyBottomBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenNavigation.Main.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = ScreenNavigation.Main.route){
                MainScreen()
            }
            composable(route = ScreenNavigation.Favorites.route){
                FavoriteScreenPlaceHolder()
            }
        }
    }
}

@Composable
fun FavoriteScreenPlaceHolder() {

}