package com.example.cookwhatyouhave.ui.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookwhatyouhave.domain.model.MealItem
import com.example.cookwhatyouhave.usecase.DeleteMealUseCase
import com.example.cookwhatyouhave.usecase.GetFavoriteMealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteMealsUseCase: GetFavoriteMealsUseCase,
    private val deleteMealUseCase: DeleteMealUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    init {
        observeFavorites()
    }

    private fun observeFavorites(){
        viewModelScope.launch {
            getFavoriteMealsUseCase().collect { meals->
                _uiState.update { it.copy(favoriteMeals = meals) }
            }
        }
    }

    fun onMealClick(meal: MealItem){
        _uiState.update { it.copy(selectedMeal = meal) }
    }

    fun dismissDialog(){
        _uiState.update { it.copy(selectedMeal = null) }
    }

    fun removeFavorite(meal: MealItem){
        viewModelScope.launch {
            deleteMealUseCase(meal)
            if(_uiState.value.selectedMeal?.id == meal.id){
                dismissDialog()
            }
        }
    }
}