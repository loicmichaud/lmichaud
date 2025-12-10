package com.example.smp_compose.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smp_compose.ui.theme.SMPRed

@Composable
fun PresentationScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Présentation de notre entreprise",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = SMPRed,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = "Nous sommes une startup innovante dédiée à fournir des solutions de qualité pour nos clients. Notre mission est de créer des produits exceptionnels qui répondent aux besoins du marché.",
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Notre équipe passionnée travaille chaque jour pour vous apporter des solutions innovantes et de qualité.",
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"))
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = SMPRed),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Vidéo de présentation",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}
