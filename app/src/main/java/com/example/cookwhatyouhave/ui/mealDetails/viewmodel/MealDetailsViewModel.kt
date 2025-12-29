package com.example.cookwhatyouhave.ui.mealDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookwhatyouhave.domain.model.MealItem
import com.example.cookwhatyouhave.usecase.DeleteMealUseCase
import com.example.cookwhatyouhave.usecase.GetFavoriteMealsUseCase
import com.example.cookwhatyouhave.usecase.GetMealDetailsUseCase
import com.example.cookwhatyouhave.usecase.SaveMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    private val getMealDetailsUseCase: GetMealDetailsUseCase,
    private val saveMealUseCase: SaveMealUseCase,
    private val deleteMealUseCase: DeleteMealUseCase,
    private val getFavoriteMealsUseCase: GetFavoriteMealsUseCase
) : ViewModel(){
    private val _uiState = MutableStateFlow(MealDetailsUiState())
    val uiState: StateFlow<MealDetailsUiState> = _uiState.asStateFlow()

    fun loadMealDetails(id: String){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true,error = null) }

            getMealDetailsUseCase(id)
                .onSuccess { meals ->
                    val foundMeal = meals.firstOrNull()
                    _uiState.update { it.copy(isLoading = false,meal = foundMeal) }

                    checkIfFavorite(id)
                }
                .onFailure { error->
                    _uiState.update { it.copy(isLoading = false,error = error.message) }
                }
        }
    }

    private fun checkIfFavorite(id: String){
        viewModelScope.launch {
            getFavoriteMealsUseCase().collect { favorites->
                val isFav = favorites.any{it.id==id}
                _uiState.update { it.copy(isFavorite = isFav) }
            }
        }
    }

    fun toggleFavorite(meal: MealItem){
        viewModelScope.launch {
            if(uiState.value.isFavorite){
                deleteMealUseCase(meal)
            }else{
                saveMealUseCase(meal)
            }
        }
    }
}