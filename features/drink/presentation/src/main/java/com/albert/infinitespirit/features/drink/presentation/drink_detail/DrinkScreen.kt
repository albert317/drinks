package com.albert.infinitespirit.features.drink.presentation.drink_detail

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.albert.infinitespirit.designsystem.image.ImageLoaderUIKit
import com.albert.infinitespirit.features.drink.domain.Drink

@Composable
fun DrinkScreen(popBackStack: () -> Unit) {
    val viewModel: DrinkViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()
    val mContext = LocalContext.current
    state.drink?.let {
        DrinkDetailContent(
            it,
            {
                popBackStack()
            }, {
                shareDrink(it, mContext)
            }
        )
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkDetailContent(
    drink: Drink,
    popBackStack: () -> Unit = {},
    shareDrink: (Drink) -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.background(color = Color.Red),
                title = {
                    drink.name?.let {
                        Text(
                            text = it,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null)
                    }
                    IconButton(onClick = { shareDrink(drink) }) {
                        Icon(imageVector = Icons.Outlined.Share, contentDescription = null)
                    }
                },
            )
        }, content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                LazyColumn {
                    item {
                        Box(
                            modifier = Modifier.background(
                                MaterialTheme.colorScheme.surfaceColorAtElevation(
                                    3.dp
                                )
                            )
                        ) {
                            drink.photo?.let {
                                ImageLoaderUIKit(
                                    url = it,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(280.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }

                        Text(
                            text = drink.description ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            textAlign = TextAlign.Justify
                        )

                        Text(
                            text = "Origen: ${drink.origin}",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                        if (drink.ingredients?.isNotEmpty() == true) {
                            Text(
                                text = "Ingredientes",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                            drink.ingredients?.forEachIndexed { index, s ->
                                Row {
                                    Text(
                                        text = "${index + 1}.",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(
                                            start = 16.dp,
                                            top = 8.dp
                                        )
                                    )
                                    Text(
                                        text = s,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 8.dp, end = 16.dp, top = 8.dp),
                                        textAlign = TextAlign.Justify
                                    )
                                }

                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        if (drink.preparationSteps?.isNotEmpty() == true) {
                            Text(
                                text = "Preparación",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                            drink.preparationSteps?.forEachIndexed { index, s ->
                                Row {
                                    Text(
                                        text = "${index + 1}.",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(
                                            start = 16.dp,
                                            top = 8.dp
                                        )
                                    )
                                    Text(
                                        text = s,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 8.dp, end = 16.dp, top = 8.dp),
                                        textAlign = TextAlign.Justify
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }
    )
}


fun shareDrink(drink: Drink, context: Context) {

    val message =
        "Mira esta bebida: ${drink.name}! Descubre cómo prepararla en nuestra app: <tu enlace aquí>"
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, message)
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(shareIntent, null))
}

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun DrinkDetailContentPreview() {
    DrinkDetailContent(
        drink = Drink(
            id = "1",
            name = "Drink Name",
            description = "Drink Description",
            origin = "Drink Origin",
            photo = "https://www.example.com/image.jpg",
            ingredients = listOf("Ingredient 1", "Ingredient 2", "Ingredient 3", "Ingredient 4"),
            preparationSteps = listOf("Step 1", "Step 2")
        )
    )
}