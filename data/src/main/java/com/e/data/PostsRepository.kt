package com.e.data

import com.e.domain.Post

class PostsRepository (val remoteDataSource: IRemoteDataSource){
    fun getAllPosts(): List<Post> = remoteDataSource.getAllPosts()
    fun addPost(post: Post) = remoteDataSource.addPost(post)
}