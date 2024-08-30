package com.example.mobilneprojekat_1.cats_preview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

import com.example.mobilneprojekat_1.core.theme.MobilneProjekat_1Theme

fun NavGraphBuilder.catPreviewScreen(
    route: String,
    navController: NavController,
) = composable(route = route) {
    val catsPreviewViewModel = viewModel<CatPreviewViewModel>()
    val state by catsPreviewViewModel.state.collectAsState()

    CatPreviewScreen(
        state = state,
        onBack = { navController.popBackStack() }
//        onItemClick = { breed ->
//            navController.navigate("breed/details/${breed.id}")
//        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatPreviewScreen(
    state: CatPreviewState,
    onBack: () -> Unit,
) {
    // Assuming state contains a list of CatUiModel
    val cat = state.catUiModel ?: return

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Cat Preview") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = cat.imageUrl),
                contentDescription = "${cat.name} image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = cat.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = cat.altNames.joinToString(", "),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = cat.description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Temperament: ${cat.temperament.take(3).joinToString(", ")}",
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // Add navigation to details here when ready
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "View Details")
            }
        }
    }
}
