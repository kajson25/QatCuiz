package com.example.mobilneprojekat_1.cats.preview

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mobilneprojekat_1.cats.list.CatListScreen
import com.example.mobilneprojekat_1.cats.list.CatListViewModel

import com.example.mobilneprojekat_1.core.theme.MobilneProjekat_1Theme

fun NavGraphBuilder.catPreviewScreen(
    route: String,
    navController: NavController,
) = composable(route = route) {
    val catsListViewModel = viewModel<CatListViewModel>()
    val state by catsListViewModel.state.collectAsState()

    CatListScreen(
        state = state,
        eventPublisher = { catsListViewModel.setEvent(it) },
        onItemClick = { breed ->
            navController.navigate("breed/details/${breed.id}")
//            navController.navigate("breeds/${breed.id}/facts")
        }
    )
}

@Composable
fun PreviewScreen(){

}

@Composable
fun ItemPreview(
        name: String,
        text: String,
        onNavigate: () -> Unit
    ){
        Button(onClick = onNavigate) {
            Text(name)
            Text(text = text)
        }
}


@Preview(showBackground = true)
@Composable
fun CatScreenPreview() {
    MobilneProjekat_1Theme {
        PreviewScreen()
    }
}
