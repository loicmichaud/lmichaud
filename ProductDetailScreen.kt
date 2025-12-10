package com.example.smp_compose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smp_compose.data.ProductRepository
import com.example.smp_compose.ui.theme.SMPRed

@Composable
fun ProductDetailScreen(
    productId: Int,
    navController: NavController
) {
    val product = ProductRepository.getProductById(productId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Retour",
                    tint = SMPRed
                )
            }
            Text(
                text = product?.name ?: "Produit",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = SMPRed,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        // Images
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color(0xFFE0E0E0)),
                contentAlignment = Alignment.Center
            ) {
                Text("Photo 1", color = Color.Gray)
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color(0xFFE0E0E0)),
                contentAlignment = Alignment.Center
            ) {
                Text("Photo 2", color = Color.Gray)
            }
        }

        product?.let {
            Text(
                text = it.description,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Text(
                text = "Prix: ${it.price} €",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50),
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { /* TODO: Open 3D model */ },
                colors = ButtonDefaults.buttonColors(containerColor = SMPRed),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Voir modèle 3D",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Button(
                onClick = { /* TODO: Open PDF plans */ },
                colors = ButtonDefaults.buttonColors(containerColor = SMPRed),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Plans (PDF)",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Button(
                onClick = { /* TODO: Play video */ },
                colors = ButtonDefaults.buttonColors(containerColor = SMPRed),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Lire la vidéo",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Retour au catalogue",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}
