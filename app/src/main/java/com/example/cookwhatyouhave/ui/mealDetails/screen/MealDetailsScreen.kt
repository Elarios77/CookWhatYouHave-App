package com.example.cookwhatyouhave.ui.mealDetails.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cookwhatyouhave.ui.main.screen.RecipeDetailPopup
import com.example.cookwhatyouhave.ui.mealDetails.viewmodel.MealDetailsViewModel

@Composable
fun RecipeDetailsDialog(
    mealId:String,
    onDismiss:()-> Unit,
    viewModel: MealDetailsViewModel = hiltViewModel()
){
    val uiState  by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(mealId)
    {
        viewModel.loadMealDetails(mealId)
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        if(uiState.isLoading){
            Box(modifier = Modifier.fillMaxSize().background(Color.White),
                contentAlignment = Alignment.Center)
            {
                CircularProgressIndicator(color = Color(0xFFFF9800))
            }
        }else if(uiState.meal!=null){
            RecipeDetailPopup(
                meal = uiState.meal!!,
                isFavorite = uiState.isFavorite,
                onDismiss = onDismiss,
                onFavoriteToggle = {viewModel.toggleFavorite(uiState.meal!!)}
            )
        }else if(uiState.error!=null){
            Box(modifier = Modifier.fillMaxSize().background(Color.White),
                contentAlignment = Alignment.Center)
            {
                Text("Error: ${uiState.error}", color = Color.Red)
            }
        }
    }
}