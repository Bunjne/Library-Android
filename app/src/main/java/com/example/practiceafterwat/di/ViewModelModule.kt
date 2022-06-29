package com.example.practiceafterwat.di

import com.example.practiceafterwat.data.viewModel.MazeShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MazeShowViewModel(get()) }
}