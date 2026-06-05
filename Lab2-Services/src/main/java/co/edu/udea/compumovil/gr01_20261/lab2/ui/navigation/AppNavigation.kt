package co.edu.udea.compumovil.gr01_20261.lab2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.edu.udea.compumovil.gr01_20261.lab2.ui.screens.ChatScreen
import co.edu.udea.compumovil.gr01_20261.lab2.ui.screens.ConversationListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "conversation_list"
    ) {
        composable("conversation_list") {
            ConversationListScreen(
                onConversationClick = { id ->
                    navController.navigate("chat/$id")
                }
            )
        }
        composable(
            route = "chat/{conversationId}",
            arguments = listOf(
                navArgument("conversationId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("conversationId") ?: ""
            ChatScreen(
                conversationId = id,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}