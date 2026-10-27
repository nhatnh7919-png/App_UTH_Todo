package com.example.app_uth_todo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_uth_todo.screens.HomeScreen
import com.example.app_uth_todo.screens.OnboardingScreen
import com.example.app_uth_todo.screens.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {

        // 1. Màn hình Splash
        composable(route = "splash") {
            SplashScreen(onTimeout = {
                navController.navigate("onboarding") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }

        // 2. Màn hình Onboarding
        composable(route = "onboarding") {
            OnboardingScreen(onNavigateToHome = {
                navController.navigate("home") {
                    // Xóa Onboarding khỏi lịch sử để không quay lại được khi bấm nút Back
                    popUpTo("onboarding") { inclusive = true }
                }
            })
        }

        // 3. Màn hình Home (Trang mới)
        composable(route = "home") {
            HomeScreen()
        }
    }
}