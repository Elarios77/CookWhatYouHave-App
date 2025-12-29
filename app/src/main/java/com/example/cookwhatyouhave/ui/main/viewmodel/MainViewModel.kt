package com.example.cookwhatyouhave.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookwhatyouhave.usecase.GetMealsByIngredientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMealsByIngredientUseCase: GetMealsByIngredientUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()
    fun onSearchChange(text:String){
        _uiState.update { it.copy(
            searchQuery = text.trim(),
            searchPerformed = false
        ) }

        if(text.isBlank()){
            _uiState.update { it.copy(searchResults = emptyList()) }
        }
    }

    fun searchRecipes(){
        val query = _uiState.value.searchQuery
        if(query.isBlank()) return
        val formattedQuery = if(query.endsWith("s",ignoreCase = true)){
            query.dropLast(1)
        }else{
            query
        }
         viewModelScope.launch {
             _uiState.update { it.copy(isLoading = true, error = null) }

             getMealsByIngredientUseCase(formattedQuery)
             .onSuccess { meals->
                 _uiState.update { it.copy(
                     isLoading = false,
                     searchResults = meals,
                     searchPerformed = true
                 ) }
             }.onFailure { error->
                 _uiState.update { it.copy(
                     isLoading = false,
                     error = error.message,
                     searchPerformed = true
                 ) }
             }
         }
    }
}