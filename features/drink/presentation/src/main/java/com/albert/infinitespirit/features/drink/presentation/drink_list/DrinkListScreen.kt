package com.albert.infinitespirit.features.drink.presentation.drink_list

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.albert.infinitespirit.features.drink.domain.Drink

@Composable
fun DrinkListScreen(
    goToDrink: (String) -> Unit
) {
    val viewModel: DrinkListViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.setIntent(DrinkListIntent.LoadDrinkList)
    }

    DrinkListContent(
        drinks = state.drinks,
        viewModel::searchDrinks,
        viewModel::setIntent
    )

    LaunchedEffect(viewModel.navigateToDetailEvent) {
        viewModel.navigateToDetailEvent.collect {
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
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = null)
                }
            },
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
            label = { Text("Buscar") },
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
            Card(Modifier.size(85.dp)) {
                item.photo?.let { ImageLoader(url = it) }
            }
            Column(
                Modifier
                    .padding(vertical = 14.dp, horizontal = 14.dp)
                    .weight(1f)
            ) {
                item.name?.let {
                    Text(
                        text = it, maxLines = 1,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W500)
                    )
                }
                item.origin?.let {
                    Text(
                        text = it, maxLines = 1,
                        color = MaterialTheme.colorScheme.outline,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Composable
fun ImageLoader(url: String) {
    val image = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = url)
            .apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
            }).build()
    )
    Image(painter = image, contentDescription = null)
}

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun Greeting() {
    DrinkListContent(
        drinks = listOf(
            Drink(name = "Pisco Sour", origin = "Peru"),
            Drink(name = "Mojito", origin = "Cuba"),
            Drink(name = "Caipirinha", origin = "Brazil"),
            Drink(name = "Margarita", origin = "Mexico"),
            Drink(name = "Martini", origin = "USA"),
            Drink(name = "Negroni", origin = "Italy"),
        ),
        searchFunction = {}
    )
}