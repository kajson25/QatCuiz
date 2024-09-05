package com.example.mobilneprojekat_1.user.user_profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.catapult.data.database.entities.ResultDbModel
import com.example.mobilneprojekat_1.navigation.AppDrawer
import com.example.mobilneprojekat_1.navigation.HamburgerMenu

fun NavGraphBuilder.profileScreen(
    route: String,
    navController: NavController,
) = composable(route = route) {

    val profileViewModel = hiltViewModel<ProfileViewModel>()
    val state by profileViewModel.state.collectAsState()

    ProfileScreen(
        state = state,
        onEditClick = { navController.navigate("profile/edit") },
        navController = navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    state: ProfileContract.ProfileState,
    onEditClick: () -> Unit,
    navController: NavController,
) {
    if (state.fetchingData) {
        // Show a loading indicator while data is being fetched
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
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
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopAppBar(
                    title = { Text("Kajina aplikacija") },
                    navigationIcon = { HamburgerMenu(drawerState = drawerState) },
                )
                // User data section
                Text(text = "Profile", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(16.dp))

                // Display the user's name and username
                Text(text = "Name: ${state.userData.firstName}")
                Text(text = "Username: ${state.userData.nickname}")
                Spacer(modifier = Modifier.height(16.dp))

                // Best global rank
                Text(text = "Best Global Rank: ${state.bestGlobalRank.first} (${state.bestGlobalRank.second} points)")
                Spacer(modifier = Modifier.height(16.dp))

                // Total games played
                Text(text = "Total Games Played: ${state.totalGamesPlayed}")
                Spacer(modifier = Modifier.height(16.dp))

                // Quiz results (display list of results)
                Text(text = "Quiz Results:")
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    items(state.quizResults) { result ->
                        QuizResultItem(result = result)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Edit Profile button
                Button(onClick = onEditClick) {
                    Text(text = "Edit Profile")
                }
            }
        }
    }
}

@Composable
fun QuizResultItem(result: ResultDbModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(text = "Username: ${result.username}", style = MaterialTheme.typography.titleSmall)
        Text(text = "Score: ${result.result}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Date: ${result.createdAt}", style = MaterialTheme.typography.bodyMedium)
    }
}
