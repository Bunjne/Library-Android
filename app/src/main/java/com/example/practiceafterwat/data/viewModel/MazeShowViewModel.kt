package com.example.practiceafterwat.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiceafterwat.data.model.Show
import com.example.practiceafterwat.data.repository.MazeShowRepositoryImpl
import com.example.practiceafterwat.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MazeShowViewModel(private val showRepositoryImpl: MazeShowRepositoryImpl): ViewModel() {

    private val _showListState = MutableStateFlow<Resource<List<Show>>>(Resource.loading())
    val showListState: StateFlow<Resource<List<Show>>>
        get() = _showListState

    fun getShowList() {
        viewModelScope.launch {
            showRepositoryImpl.getShowList().collect {
                _showListState.value = it
            }
        }
    }
}