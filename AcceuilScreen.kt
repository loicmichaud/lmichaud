package com.example.smp_compose.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smp_compose.ui.theme.SMPRed


@Composable
fun AcceuilScreen(){
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Bienvenue chez SMP",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = SMPRed,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Text(
            text = "Découvrez nos produits et services",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"))
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = SMPRed),
            modifier = Modifier.padding(16.dp)
        ){
            Text(
                text = "Vidéo de présentation",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0))
        ){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "SMP",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = SMPRed
                )
            }
        }
    }
}
