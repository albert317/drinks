package com.albert.infinitespirit.home.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.albert.infinitespirit.designsystem.image.ImageLoaderUIKit
import com.albert.infinitespirit.features.drink.presentation.drink_list.DrinkListScreen
import com.albert.infinitespirit.features.drink.presentation.drink_list.drinkListContentPreview
import com.albert.infinitespirit.home.presentation.R
import kotlinx.coroutines.launch
import com.albert.infinitespirit.designsystem.R as DesignSystemR


@Composable
fun HomeScreen(goToDrink: (String) -> Unit = {}) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.setIntent(HomeIntent.LoadDataUser)
    }

    HomeScreenContent(state, goToDrink)

}

@Composable
fun HomeScreenContent(
    state: HomeUiState = HomeUiState(),
    goToDrink: (String) -> Unit = {}
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var selectedItem by remember { mutableStateOf("Inbox") }
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Box(
                Modifier
                    .width(300.dp)
                    .fillMaxHeight()
            ) {
                MyModalDrawerSheet(
                    state = state,
                    openDrawer = {
                        scope.launch { drawerState.open() }
                    },
                    drawerState = drawerState,
                    selectedItem = selectedItem,
                    changeSelectedItem = { selectedItem = it }
                )
            }
        }
    ) {
        HomeNavigationBard(
            goToDrink = goToDrink
        ) {
            scope.launch { drawerState.open() }
        }
    }
}

@Composable
fun MyModalDrawerSheet(
    state: HomeUiState = HomeUiState(),
    openDrawer: () -> Unit,
    drawerState: DrawerState,
    selectedItem: String = "Inbox",
    changeSelectedItem: (String) -> Unit = {}
) {
    ModalDrawerSheet(
        Modifier
            .width(300.dp)
            .fillMaxHeight()
    ) {
        Spacer(Modifier.height(40.dp))
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageLoaderUIKit(
                state.photoUrl,
                contentScale = ContentScale.Crop, modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Text(
                text = state.userName,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W500),
                modifier = Modifier.padding(top = 10.dp)
            )
        }
        Spacer(Modifier.height(20.dp))
        NavigationDrawerItem(
            icon = {
                Icon(
                    ImageVector.vectorResource(R.drawable.baseline_attach_file_24),
                    contentDescription = null
                )
            },
            label = {
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        "Inbox",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500)
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        "24",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500)
                    )
                }
            },
            selected = selectedItem == "Inbox",
            onClick = {
                //scope.launch { drawerState.close() }
                openDrawer()
                changeSelectedItem("Inbox")
                //selectedItem = "Inbox"
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            icon = {
                Icon(
                    ImageVector.vectorResource(R.drawable.baseline_attach_file_24),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    "Favorites",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500)
                )
            },
            selected = selectedItem == "Favorites",
            onClick = {
                //scope.launch { drawerState.close() }
                //selectedItem = "Favorites"
                changeSelectedItem("Favorites")
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        Divider(
            Modifier
                .padding(horizontal = 25.dp)
                .padding(top = 15.dp)
        )
        Text(
            text = "Personal Folder",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W500),
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
        )
    }
}

@Composable
fun HomeNavigationBard(
    goToDrink: (String) -> Unit = {},
    openDrawer: () -> Unit,
) {
    var selectedItem by remember { mutableStateOf("Home") }

    Scaffold(
        content = { innerPadding ->
            when (selectedItem) {
                "Home" -> {
                    Column(modifier = Modifier.padding(innerPadding)) {
                        val isInPreview = LocalInspectionMode.current
                        if (isInPreview) {
                            drinkListContentPreview()
                        } else {
                            DrinkListScreen(goToDrink = goToDrink, openDrawer = {
                                openDrawer()
                            })
                        }
                    }

                }

                "Guardados" -> {
                    Text(text = "Guardados")
                }

                "Perfil" -> {
                    Text(text = "Perfil")
                }
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = {
                        Icon(
                            ImageVector.vectorResource(id = DesignSystemR.drawable.ic_view_agenda),
                            contentDescription = "Home"
                        )
                    },
                    selected = selectedItem == "Home",
                    onClick = { selectedItem = "Home" }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            ImageVector.vectorResource(id = DesignSystemR.drawable.ic_bookmark),
                            contentDescription = "Guardados"
                        )
                    },
                    selected = selectedItem == "Guardados",
                    onClick = { selectedItem = "Guardados" }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            ImageVector.vectorResource(id = DesignSystemR.drawable.ic_account_circle),
                            contentDescription = "Perfil"
                        )
                    },
                    selected = selectedItem == "Perfil",
                    onClick = { selectedItem = "Perfil" }
                )
            }
        }
    )
}

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewHomeScreen() {
    HomeScreenContent()
}

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewMyModalDrawerSheet() {
    MyModalDrawerSheet(
        openDrawer = {},
        drawerState = rememberDrawerState(DrawerValue.Closed)
    )
}