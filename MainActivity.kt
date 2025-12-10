package com.example.smp_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.smp_compose.navigation.Screen
import com.example.smp_compose.ui.screens.*
import com.example.smp_compose.ui.theme.SMPComposeTheme
import com.example.smp_compose.ui.theme.SMPRed

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SMPComposeTheme {
                SMPApp()
            }
        }
    }
}

@Composable
fun SMPApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Déterminer l'écran actuel pour les boutons de navigation
    val currentScreen = when {
        currentRoute?.startsWith("product_list") == true -> Screen.Catalogue.route
        currentRoute?.startsWith("product_detail") == true -> Screen.Catalogue.route
        else -> currentRoute ?: Screen.Accueil.route
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Bannière supérieure (15%)
            TopBanner(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.5f)
            )

            // Barre de navigation (15%)
            SMPNavigationBar(
                currentScreen = currentScreen,
                onScreenSelected = { screen ->
                    navController.navigate(screen) {
                        popUpTo(Screen.Accueil.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.5f)
            )

            // Zone de contenu (70%)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f)
            ) {
                NavigationGraph(navController = navController)
            }
        }
    }
}

@Composable
fun TopBanner(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SMP",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun SMPNavigationBar(
    currentScreen: String,
    onScreenSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val screens = listOf(
                Screen.Accueil.route to "Accueil",
                Screen.Presentation.route to "Présentation",
                Screen.Catalogue.route to "Catalogue",
                Screen.Contact.route to "Contact",
                Screen.Parametres.route to "Paramètres"
            )

            screens.forEach { (route, title) ->
                NavigationButton(
                    text = title,
                    isSelected = currentScreen == route,
                    onClick = { onScreenSelected(route) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun NavigationButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) SMPRed else Color.White,
            contentColor = if (isSelected) Color.White else Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (isSelected) 0.dp else 2.dp
        )
    ) {
        Text(
            text = text,
            fontSize = 14.sp
        )
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Accueil.route
    ) {
        composable(Screen.Accueil.route) {
            AccueilScreen()
        }
        composable(Screen.Presentation.route) {
            PresentationScreen()
        }
        composable(Screen.Catalogue.route) {
            CatalogueScreen(navController)
        }
        composable(Screen.Contact.route) {
            ContactScreen()
        }
        composable(Screen.Parametres.route) {
            ParametresScreen()
        }
        composable(
            route = Screen.ProductList.route,
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            ProductListScreen(category = category, navController = navController)
        }
        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailScreen(productId = productId, navController = navController)
        }
    }
}
