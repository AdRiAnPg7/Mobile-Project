package com.e.data

import com.e.domain.Post

class PostsRepository (val remoteDataSource: IRemoteDataSource){
    suspend fun getAllPosts(): List<Post> = remoteDataSource.getAllPosts()
}