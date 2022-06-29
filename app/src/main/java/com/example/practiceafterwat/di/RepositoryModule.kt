package com.example.practiceafterwat.di

import com.example.practiceafterwat.data.repository.MazeShowRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    factory { MazeShowRepositoryImpl(get(), get(named("io")), get()) }
}