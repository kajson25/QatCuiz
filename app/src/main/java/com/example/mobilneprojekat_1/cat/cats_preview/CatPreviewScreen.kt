package com.example.mobilneprojekat_1.cat.cats_preview

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

import com.example.mobilneprojekat_1.core.theme.MobilneProjekat_1Theme
import com.example.mobilneprojekat_1.navigation.AppDrawer
import com.example.mobilneprojekat_1.navigation.HamburgerMenu
import com.example.mobilneprojekat_1.navigation.Navigation

fun NavGraphBuilder.catPreviewScreen(
    route: String,
    arguments: List<NamedNavArgument>,
    navController: NavController,
) = composable(route = route, arguments = arguments) { backStackEntry ->

    val catId = backStackEntry.arguments?.getString("breedId") ?: ""
    val catsPreviewViewModel = hiltViewModel<CatPreviewViewModel>()
    val state by catsPreviewViewModel.state.collectAsState()
    Log.d("Kaja", "Breed id: $catId")
    Log.d("Kaja", "Cat id: ${backStackEntry.arguments?.getString("catId")}")
    state.catId = catId

    CatPreviewScreen(
        state = state,
        onBack = { navController.popBackStack() },
        navController = navController,
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
    navController: NavController,
) {
    // Assuming state contains a list of CatUiModel
    Log.d("KAJA", "Renderujem Preview: $state")
    val cat = state.catUiModel ?: return
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                navController = navController,
                drawerState = drawerState,
                scope = scope
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            TopAppBar(
                title = { Text("Kajina aplikacija") },
                navigationIcon = { HamburgerMenu(drawerState = drawerState) },
            )
            // Top App Bar
            Log.d("KAJA", "Macka u renderu: ${cat.name}")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Cat Preview",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            // Content
            Log.d("KAJA", "Padding: No Padding")
            Column(
                modifier = Modifier
                    .fillMaxSize()
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
}
