package com.e.usescases

import com.e.data.PostsRepository
import com.e.domain.Post

class AddPost(val postRepository: PostsRepository, val post: Post) {
    suspend fun invoke() {
        postRepository.addPost(post)
    }
}