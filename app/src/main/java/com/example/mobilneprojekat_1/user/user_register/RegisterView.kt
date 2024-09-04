package com.example.mobilneprojekat_1.user.user_register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.registerScreen(
    route: String,
    onRegisterSuccess: () -> Unit // Optional: Callback for navigation after successful registration
) = composable(route = route) {

    // Get the RegisterViewModel using Hilt
    val registerViewModel = hiltViewModel<RegisterViewModel>()

    // Create mutable states for user inputs
    var name by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    // State to track loading and error
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Setup success and error callbacks
    LaunchedEffect(key1 = Unit) {
        registerViewModel.setOnRegisterSuccessListener {
            isLoading = false
            onRegisterSuccess() // Navigate or handle success action
        }

        registerViewModel.setOnRegisterErrorListener { error ->
            isLoading = false
            errorMessage = error // Display error in the UI
        }
    }

    // UI for the registration screen
    RegisterScreen(
        name = name,
        onNameChange = { name = it },
        nickname = nickname,
        onNicknameChange = { nickname = it },
        email = email,
        onEmailChange = { email = it },
        isLoading = isLoading,
        onRegister = {
            isLoading = true
            registerViewModel.setEvent(RegisterContract.RegisterEvent.Register(name, nickname, email))
        },
        errorMessage = errorMessage
    )
}


@Composable
fun RegisterScreen(
    name: String,
    onNameChange: (String) -> Unit,
    nickname: String,
    onNicknameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    isLoading: Boolean,
    onRegister: () -> Unit,
    errorMessage: String?
) {
    // UI structure with padding and spacing
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Name input field
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Nickname input field
        OutlinedTextField(
            value = nickname,
            onValueChange = onNicknameChange,
            label = { Text("Nickname") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Email input field
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Display error message if there's any
        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }

        // Show loading indicator or Register button
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = onRegister,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Register")
            }
        }
    }
}
