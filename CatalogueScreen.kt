package com.example.smp_compose.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smp_compose.navigation.Screen
import com.example.smp_compose.ui.theme.SMPRed

@Composable
fun CatalogueScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Notre catalogue de produits",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = SMPRed,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CategoryCard(
                title = "Perceuses",
                onClick = {
                    navController.navigate(Screen.ProductList.createRoute("Perceuses"))
                }
            )
            CategoryCard(
                title = "Flexibles",
                onClick = {
                    navController.navigate(Screen.ProductList.createRoute("Flexibles"))
                }
            )
            CategoryCard(
                title = "Tuyaux",
                onClick = {
                    navController.navigate(Screen.ProductList.createRoute("Tuyaux"))
                }
            )
        }
    }
}

@Composable
fun CategoryCard(
    title: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(100.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}
