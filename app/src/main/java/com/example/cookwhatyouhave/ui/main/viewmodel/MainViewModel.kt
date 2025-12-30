package com.example.cookwhatyouhave.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookwhatyouhave.usecase.GetMealsByIngredientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMealsByIngredientUseCase: GetMealsByIngredientUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()
    fun onSearchChange(text: String) {
        _uiState.update {
            it.copy(
                searchQuery = text,
                searchPerformed = false,
                selectedCategory = null,
                activeQuery = ""
            )
        }

        if (text.isBlank()) {
            _uiState.update {
                it.copy(
                    searchResults = emptyList(),
                    searchPerformed = false
                )
            }
        }
    }

    fun searchRecipes() {
        val query = _uiState.value.searchQuery

        if(query.isBlank()){
            _uiState.update { it.copy(
                searchResults =  emptyList(),
                searchPerformed = false
            ) }
            return
        }

        val singularQuery: String
        val pluralQuery: String

        if (query.endsWith("s", ignoreCase = true)) {
            singularQuery = query.dropLast(1)
            pluralQuery = query
        } else {
            singularQuery = query
            pluralQuery = query + "s"
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val qSingular = async { getMealsByIngredientUseCase(singularQuery) }
            val qPlural = async { getMealsByIngredientUseCase(pluralQuery) }

            val resultSingular = qSingular.await()
            val resultPlural = qPlural.await()

            val list1 = resultSingular.getOrNull() ?: emptyList()
            val list2 = resultPlural.getOrNull() ?: emptyList()

            val resultList = (list1 + list2).distinctBy { it.id }

            if (resultList.isNotEmpty()) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        searchResults = resultList,
                        searchPerformed = true
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        searchResults = emptyList(),
                        searchPerformed = true,
                        error = resultSingular.exceptionOrNull()?.message
                    )
                }
            }
        }
    }
}