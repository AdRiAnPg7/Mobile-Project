package com.e.data

import com.e.domain.Post

interface IRemoteDataSource {
    fun getAllPosts(): List<Post>
    fun addPost(post: Post)
}
