package com.example.practiceafterwat.di

import com.example.practiceafterwat.BuildConfig
import com.example.practiceafterwat.data.source.remote.MazeShowService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    single {
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BuildConfig.TV_MAZE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory { get<Retrofit>().create(MazeShowService::class.java) }
}