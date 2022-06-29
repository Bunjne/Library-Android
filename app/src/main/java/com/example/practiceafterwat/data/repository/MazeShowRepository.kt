package com.example.practiceafterwat.data.repository

import android.content.Context
import com.example.practiceafterwat.R
import com.example.practiceafterwat.data.model.Show
import com.example.practiceafterwat.data.source.remote.MazeShowService
import com.example.practiceafterwat.util.Resource
import com.example.practiceafterwat.util.isNetworkAvailable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface MazeShowRepository {
    suspend fun getShowList(): Flow<Resource<List<Show>>>
}

class MazeShowRepositoryImpl(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher,
    private val remote: MazeShowService
) : MazeShowRepository {
    override suspend fun getShowList(): Flow<Resource<List<Show>>> {
        return flow {
            emit(Resource.loading())
            if (context.isNetworkAvailable()) {
                try {
                    val response = remote.getShowList()
                    emit(Resource.success(response))
                } catch (t: Throwable) {
                    emit(Resource.error(context.getString(R.string.failed_loading_msg)))
                }
            } else {
                emit(Resource.error(context.getString(R.string.failed_network_msg)))
            }
        }.flowOn(dispatcher)
    }
}