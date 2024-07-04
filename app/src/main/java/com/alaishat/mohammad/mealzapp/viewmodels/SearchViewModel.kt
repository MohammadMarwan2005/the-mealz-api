package com.alaishat.mohammad.mealzapp.viewmodels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaishat.mohammad.domain.model.MealsListResonse.MealsListResponse
import com.alaishat.mohammad.domain.uscase.GetSearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase
): ViewModel() {
    private val _searchResultResponse: MutableStateFlow<MealsListResponse?> = MutableStateFlow(null)
    val searchResultResponse: StateFlow<MealsListResponse?> = _searchResultResponse.asStateFlow()

    private val _isLoadingSearchResult: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadingSearchResult: StateFlow<Boolean> = _isLoadingSearchResult.asStateFlow()


    fun getSearchResultOn(searchQuery: String) {

        viewModelScope.launch {
            _isLoadingSearchResult.value = true
            try {
                _searchResultResponse.value = getSearchResultUseCase.invoke(searchQuery)
            } catch (e: Exception) {

            } finally {
                _isLoadingSearchResult.value = false
            }
        }
    }
}