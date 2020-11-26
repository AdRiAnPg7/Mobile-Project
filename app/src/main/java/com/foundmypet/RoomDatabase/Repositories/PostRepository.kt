package com.foundmypet.RoomDatabase.Repositories

import com.foundmypet.RoomDatabase.Entities.Post
import com.foundmypet.RoomDatabase.dao.IPostDao

class PostRepository(private val postdao: IPostDao) {

    suspend fun insert(book: Post) {
        postdao.insert(book)
    }

    fun getListBooks(): List<Post> {
        return postdao.getList()
    }
}
