package com.example.mobilneprojekat_1.cats.list

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mobilneprojekat_1.cats.domain.Cat
import com.example.mobilneprojekat_1.cats.preview.ItemPreview
import com.example.mobilneprojekat_1.core.compose.ErrorData
import com.example.mobilneprojekat_1.core.compose.FetchingData
import com.example.mobilneprojekat_1.core.compose.NoData

fun NavGraphBuilder.catsListScreen(
    route: String,
    navController: NavController,
) = composable(route = route) {
    val catsListViewModel = viewModel<CatListViewModel>()
    val state by catsListViewModel.state.collectAsState()

    CatListScreen(
        state = state,
        eventPublisher = { catsListViewModel.setEvent(it) },
        onItemClick = { breed ->
//            navController.navigate("breed/details/${breed.id}")
            navController.navigate("breeds/${breed.id}/facts")
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListScreen(
    state : CatListState,
    eventPublisher : (CatListUIEvent) -> Unit,
    onItemClick: (Cat) -> Unit
) {

    Scaffold (
        // TOP BAR
        topBar = {
            if(!state.searchActive) {
                TopAppBar(
                    title = { Text("Kajina aplikacija") },
                    actions = {
                        IconButton(onClick = { eventPublisher(CatListUIEvent.StartSearch) }) {
                            Icon(Icons.Filled.Search, contentDescription = "Search")
                        }
                    }
                )
            }
        },

        content = { paddingValues ->

            if (state.searchActive) {
                Log.d("KAJA", "Entering search screen")
                SearchScreen(state, eventPublisher, onItemClick)
            } else {
                AllExpendableItems(
                    cats = state.catsAll,
                    paddingValues = paddingValues,
                    onItemClick = onItemClick
                )

                if (state.catsAll.isEmpty()) {
                    when {
                        state.fetching -> FetchingData()
                        state.error is CatListState.CatListError.CatListUpdateFailed ->
                            ErrorData(
                                errorMessage = state.error.cause?.message ?: "Failed to load."
                            )

                        else -> NoData()
                    }
                }
            }
        },

        // BOTTOM BAR
    )

}

@Composable
fun SearchScreen(
    state: CatListState,
    eventPublisher: (CatListUIEvent) -> Unit,
    onItemClick: (Cat) -> Unit
) {
    Column {
        SearchBar(
            filterString = state.filter,
            eventPublisher = eventPublisher
        )
        AllExpendableItems(
            cats = state.catsFiltered,
            paddingValues = PaddingValues(),
            onItemClick = onItemClick
        )
    }
}

@Composable
fun SearchBar(
    filterString: String,
    eventPublisher : (CatListUIEvent) -> Unit
) {

    var searchText by remember { mutableStateOf(filterString) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { eventPublisher(CatListUIEvent.StopSearch) }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }

        TextField(
            value = searchText,
            onValueChange = { newValue ->
                searchText = newValue
                eventPublisher(CatListUIEvent.SearchChanged(newValue))
                Log.d("KAJA", "SearchChanged event sent: $newValue")
            },
            modifier = Modifier.weight(1f),
            placeholder = { Text("Search") }
        )

        IconButton(onClick = {
            searchText = ""
            eventPublisher(CatListUIEvent.DeleteSearch)
        }) {
            Icon(Icons.Filled.Close, contentDescription = "Clear")
        }
    }
}

@Composable
fun AllExpendableItems(
    cats: List<Cat>,
    paddingValues: PaddingValues,
    onItemClick: (Cat) -> Unit
) {
    Log.d("KAJA", "AllExpendableItems: ${cats.size}")

    LazyColumn {
        items(cats.size) { index ->
            ExpandableItem(
                name = cats[index].name,
                text = cats[index].breed_group,
                onNavigate = { onItemClick(cats[index]) }
            )
        }
    }
}

@Composable
fun ExpandableItem(
    name: String,
    text: String,
    onNavigate: () -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column (modifier = Modifier.background(Color.Blue)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.LightGray)
                .border(BorderStroke(1.dp, Color.Black))
                .clickable { expanded = !expanded }
        ) {
            Text(
                text = name,
                color = Color.Black,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Filled.Add, contentDescription = "Expand/Collapse")
            }
        }
        // This Box represents the expandable content
        if (expanded) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White)
                    .border(BorderStroke(1.dp, Color.Gray))
            ) {
                // kad nije bilo wrapovano u column, bilo je dugme preko texta,
                // jer box pakuje komponente jedne na druge
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = text.take(250))
                    Button(onClick = onNavigate) {
                        Text("Go to second screen")
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ListScreenPreview() {
//    MobilneProjekat_1Theme {
//        val catViewModel: CatViewModel by viewModels()
//        SearchScreen(catViewModel)
//    }
//}