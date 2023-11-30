@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.inscryptiondeckbuilder

/*
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inscryptiondeckbuilder.ui.theme.InscryptionDeckBuilderTheme
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import kotlin.random.Random
import androidx.compose.material3.Card
*/
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.IconButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import kotlin.random.Random
import androidx.compose.material3.Card
import androidx.compose.ui.unit.sp
import com.example.inscryptiondeckbuilder.ui.theme.InscryptionDeckBuilderTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InscryptionDeckBuilderTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {

                    navigation(startDestination = "username", route = "login") {
                        composable(route = "username") {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Username",
                                    style = MaterialTheme.typography.headlineMedium
                                )
                                TextButton(onClick = {
                                    navController.navigate("password")
                                }) {
                                    Text(text = "Next")
                                }
                            }
                        }
                        composable(route = "password") {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Password",
                                    style = MaterialTheme.typography.headlineMedium
                                )
                                TextButton(onClick = {
                                    navController.navigate("tabs") {
                                        // clear back stack for nested navigation
                                        popUpTo("login")
                                    }
                                }) {
                                    Text(text = "Login")
                                }
                            }
                        }
                    }


                    composable(route = "tabs") {
                        TabsNavGraph()
                    }

                    // Optional arguments I have for the authentication
                    composable(
                        route = "account?userId={userId}",
                        arguments = listOf(navArgument("userId") { defaultValue = "1" })
                    ) { backStackEntry ->
                        val userId = backStackEntry.arguments?.getString("userId")
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Account com.example.inscryptiondeckbuilder.Screen",
                                style = MaterialTheme.typography.headlineMedium
                            )
                            Text(text = "userId=$userId")
                            TextButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Text(text = "Go Back")
                            }
                        }
                    }
                }

            }
        }
    }
}


@Composable
fun TabsNavGraph() {
    val modifier = Modifier
    val navController = rememberNavController()
    Scaffold(
        // Top bar that has a title, navigation and action icons.
        topBar = {
            // We assign the title as a box with modifiers and the actual text.
            TopAppBar(
                title = {
                    Box(
                        modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = "Deck Builder")
                    }
                },

                // Modifier add some padding and round the edges of the top bar.
                modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(20.dp)),

                // Action icon example with no implementation, placed at the end of the top bar.
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search Icon"
                        )
                    }
                },

                // Set the top bar to a desired hex colour.
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFFCCE6CC)
                )
            )
        },
        bottomBar = {
            NavigationBar(
                modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(20.dp)),
                containerColor = Color(0xFFCCE6CC)
            ) {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = backStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true,
                        label = { Text(stringResource(screen.resource)) },
                        icon = {
                            Icon(
                                imageVector = if (screen.route == "home") {
                                    Icons.Default.Home
                                } else if (screen.route == "profile") {
                                    Icons.Default.AccountBox
                                } else {
                                    Icons.Default.Star
                                },
                                contentDescription = null
                            )
                        },
                        onClick = {
                            navController.navigate(screen.route) {

                                // findStartDestination will find the actual start destination of the graph,
                                // handling cases where the graph's starting destination is itself a NavGraph
                                popUpTo(navController.graph.findStartDestination().id) {

                                    // saveState = true will save that state before it clears backstack entries up to popUpTo ID.
                                    saveState = true
                                }

                                launchSingleTop = true

                                // restoreState = true will restore that backstack
                                restoreState = true

                            }
                        },
                    )
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            navigation(startDestination = "dashboard", route = "home") {
                composable(route = "dashboard") {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(5.dp)
                    ){
                        items(30){
                            MyCard(it, modifier)
                        }
                    }
                }
            }

            composable(route = "deck"){
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(5.dp)
                ){
                    items(30){
                        MyCard(it, modifier)
                    }
                }
            }
            composable(route = "profile") {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "User Profile",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    TextButton(onClick = {
                        navController.navigate("profile_detail")
                    }) {
                        Text(text = "Profile Details")
                    }
                }
            }
        }

    }
}

val items = listOf(
    Screen.Home,
    Screen.Deck,
    Screen.Profile,
)

sealed class Screen(val route: String, @StringRes val resource: Int) {
    object Home : Screen("home", R.string.home)
    object Deck : Screen("deck", R.string.deck)
    object Profile : Screen("profile", R.string.profile)

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InscryptionDeckBuilderTheme {
        Greeting("Android")
    }
}

@Composable
fun MyCard(it: Int, modifier: Modifier){
    Card(
        modifier
            .size(100.dp)
            .padding(6.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(
                Random.nextFloat(),
                Random.nextFloat(),
                Random.nextFloat(),
                1f
            )
        )
    ) {
        Box(
            modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = it.toString(), fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }

    }
}