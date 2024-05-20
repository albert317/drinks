package com.albert.infinitespirit.features.drink.presentation.drink_list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.albert.infinitespirit.designsystem.image.ImageLoaderUIKit
import com.albert.infinitespirit.features.drink.domain.Drink

@Composable
fun DrinkListScreen(
    goToDrink: (String) -> Unit,
    openDrawer: () -> Unit = {}
) {
    val viewModel: DrinkListViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.setIntent(DrinkListIntent.LoadDrinkList)
    }

    DrinkListContent(
        openDrawer = openDrawer,
        drinks = state.drinks,
        viewModel::searchDrinks,
        viewModel::setIntent
    )

    LaunchedEffect(viewModel.navigation) {
        viewModel.navigation.collect {
            when (it) {
                is DrinkNavigationModel.DrinkDetail -> {
                    Log.d("DrinkListScreen", "DrinkDetail")
                    goToDrink(it.idDrink)
                }

                is DrinkNavigationModel.DrinkList -> {
                    Log.d("DrinkListScreen", "DrinkList")
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkListContent(
    openDrawer: () -> Unit = {},
    drinks: List<Drink>,
    searchFunction: (String) -> Unit,
    onAction: (DrinkListIntent) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.isScrollInProgress }.collect { isScrolling ->
            if (isScrolling) {
                keyboardController?.hide()
            }
        }
    }

    Column {
        CenterAlignedTopAppBar(
            title = {
                Text("Infinite Spirit", style = MaterialTheme.typography.titleLarge)
            },
            navigationIcon = {
                IconButton(onClick = {
                    openDrawer()
                }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                }
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            value = searchQuery,
            onValueChange = { newQuery ->
                searchQuery = newQuery
                searchFunction(newQuery)
            },
            label = { Text("Buscar bebida o ingrediente") },
            trailingIcon = {
                if (searchQuery != "") {
                    IconButton(onClick = {
                        searchQuery = ""
                        searchFunction(searchQuery)
                        keyboardController?.hide()
                    }) {
                        Icon(imageVector = Icons.Rounded.Clear, contentDescription = null)
                    }
                }
            }
        )
        Spacer(modifier = Modifier.size(4.dp))
        LazyColumn(state = listState, modifier = Modifier.fillMaxWidth()) {
            items(drinks) { drink ->
                DrinkItem(
                    item = drink,
                    selectedItem = { onAction(DrinkListIntent.SelectDrink(drink)) })
            }
        }
    }
}

@Composable
fun DrinkItem(
    item: Drink,
    selectedItem: (Drink) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { selectedItem(item) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Card {
                item.photo?.let {
                    ImageLoaderUIKit(
                        url = it,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(85.dp)
                    )
                }
            }
            Column(
                Modifier
                    .padding(vertical = 12.dp, horizontal = 14.dp)
                    .weight(1f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    item.name?.let {
                        Text(
                            text = it, maxLines = 1,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W500),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    item.origin?.let {
                        Text(
                            text = it, maxLines = 1,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }

                item.ingredients?.let {
                    Spacer(
                        modifier = Modifier.size(4.dp)
                    )
                    Text(
                        text = it.joinToString(", "),
                        maxLines = 2,
                        color = MaterialTheme.colorScheme.outline,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun drinkListContentPreview() {
    DrinkListContent(
        drinks = listOf(
            Drink(
                name = "Pisco Sour", origin = "Peru", photo = "https://www.example.com/image.jpg",
                ingredients = listOf(
                    "Pisco",
                    "Lemon",
                    "Sugar",
                    "Egg white",
                    "Pisco",
                    "Lemon",
                    "Sugar",
                    "Egg white",
                    "Pisco",
                    "Lemon",
                    "Sugar",
                    "Egg white",
                    "Pisco",
                    "Lemon",
                    "Sugar",
                    "Egg white"
                ),
                preparationSteps = listOf("Step 1", "Step 2")
            ),
            Drink(
                name = "Mojito",
                origin = "Cuba",
                photo = "",
                ingredients = listOf("Rum", "Mint", "Lemon", "Sugar")
            ),
            Drink(
                name = "Caipirinha",
                origin = "Brazil",
                ingredients = listOf("Cachaça", "Lemon", "Sugar")
            ),
            Drink(name = "Margarita", origin = "Mexico"),
            Drink(name = "Martini", origin = "USA"),
            Drink(name = "Negroni", origin = "Italy"),
        ),
        searchFunction = {}
    )
}