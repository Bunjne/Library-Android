package com.example.practiceafterwat.data.source.remote

import com.example.practiceafterwat.data.model.Show
import retrofit2.http.GET

interface MazeShowService {

    @GET("shows")
    suspend fun getShowList(): List<Show>
}