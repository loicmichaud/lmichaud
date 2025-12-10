package com.example.smp_compose.ui.screens

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smp_compose.ui.theme.SMPRed
import kotlinx.coroutines.launch

@Composable
fun ContactScreen() {
    var societe by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telephone by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var rgpdAccepted by remember { mutableStateOf(false) }

    var societeError by remember { mutableStateOf(false) }
    var contactError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var telephoneError by remember { mutableStateOf(false) }
    var rgpdError by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Contactez-nous",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = SMPRed,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            OutlinedTextField(
                value = societe,
                onValueChange = {
                    societe = it
                    societeError = false
                },
                label = { Text("Société *") },
                isError = societeError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = contact,
                onValueChange = {
                    contact = it
                    contactError = false
                },
                label = { Text("Contact *") },
                isError = contactError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = false
                },
                label = { Text("Email *") },
                isError = emailError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = telephone,
                onValueChange = {
                    telephone = it
                    telephoneError = false
                },
                label = { Text("Téléphone *") },
                isError = telephoneError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes") },
                minLines = 4,
                maxLines = 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = rgpdAccepted,
                    onCheckedChange = {
                        rgpdAccepted = it
                        rgpdError = false
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = SMPRed,
                        checkmarkColor = Color.White
                    )
                )
                Text(
                    text = "J'accepte que mes données personnelles soient collectées et traitées conformément à la politique de confidentialité RGPD",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp),
                    color = if (rgpdError) MaterialTheme.colorScheme.error else Color.Black
                )
            }

            Button(
                onClick = {
                    societeError = societe.isBlank()
                    contactError = contact.isBlank()
                    emailError = email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    telephoneError = telephone.isBlank()
                    rgpdError = !rgpdAccepted

                    if (!societeError && !contactError && !emailError && !telephoneError && !rgpdError) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Formulaire soumis avec succès!",
                                duration = SnackbarDuration.Short
                            )
                        }
                        societe = ""
                        contact = ""
                        email = ""
                        telephone = ""
                        notes = ""
                        rgpdAccepted = false
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Veuillez remplir tous les champs obligatoires",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = SMPRed),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = "Envoyer",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
                )
            }
        }
    }
}
