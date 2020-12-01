package com.e.data

import com.e.domain.Post

interface IRemoteDataSource {
    suspend fun getAllPosts(): List<Post>
}
