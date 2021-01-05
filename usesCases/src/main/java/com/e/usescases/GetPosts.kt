package com.e.usescases

import com.e.data.PostsRepository
import com.e.domain.Post

class GetPosts(val postsRepository: PostsRepository) {
    suspend fun invoke(): List<Post> = postsRepository.getAllPosts()
}