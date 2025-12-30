package com.example.cookwhatyouhave.ui.favorites.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.cookwhatyouhave.R
import com.example.cookwhatyouhave.domain.model.MealItem
import com.example.cookwhatyouhave.ui.favorites.viewmodel.FavoritesUiState
import com.example.cookwhatyouhave.ui.favorites.viewmodel.FavoritesViewModel
import com.example.cookwhatyouhave.ui.main.screen.RecipeDetailPopup

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    FavoritesScreenContent(
        uiState = uiState,
        onMealClick = { meal -> viewModel.onMealClick(meal) },
        onRemoveFavorite = { meal -> viewModel.removeFavorite(meal) },
        onDismissDialog = viewModel::dismissDialog
    )
}

@Composable
fun FavoritesScreenContent(
    uiState: FavoritesUiState,
    onMealClick: (MealItem) -> Unit,
    onRemoveFavorite: (MealItem) -> Unit,
    onDismissDialog: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()
        .background(Color(0xFFFFE0B2))
        .padding(8.dp)) {
        if (uiState.favoriteMeals.isEmpty()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.no_favorites),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Gray
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(uiState.favoriteMeals) { meal ->
                    FavoriteItem(
                        meal = meal,
                        onClick = { onMealClick(meal) },
                        onRemoveClick = { onRemoveFavorite(meal) }
                    )
                }
            }
        }
        if (uiState.selectedMeal != null) {
            RecipeDetailPopup(
                meal = uiState.selectedMeal,
                isFavorite = true,
                onDismiss = onDismissDialog,
                onFavoriteToggle = { onRemoveFavorite(uiState.selectedMeal) }
            )
        }
    }

}

@Composable
fun FavoriteItem(meal: MealItem, onClick: () -> Unit, onRemoveClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()
        .height(100.dp)
        .clickable{onClick()},
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        Row(modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically)
        {
            AsyncImage(
                model = meal.imageUrl,
                contentDescription = meal.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp).background(Color.LightGray)
            )
            Text(
                text = meal.name?:"Unknown recipe",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
                    .padding(horizontal = 16.dp)
            )

            IconButton(onClick = onRemoveClick,
                modifier = Modifier.padding(end = 8.dp))
            {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    val dummyMeals = listOf(
        MealItem(
            id = "1",
            name = "Spaghetti Carbonara",
            imageUrl = "",
            instructions = "Cook pasta...",
            ingredients = listOf("Pasta", "Eggs", "Bacon")
        ),
        MealItem(
            id = "2",
            name = "Chicken Curry",
            imageUrl = "",
            instructions = "Fry chicken...",
            ingredients = listOf("Chicken", "Curry", "Rice")
        )
    )
    FavoritesScreenContent(
        uiState = FavoritesUiState(favoriteMeals = dummyMeals),
        onMealClick = {},
        onRemoveFavorite = {},
        onDismissDialog = {}
    )
}