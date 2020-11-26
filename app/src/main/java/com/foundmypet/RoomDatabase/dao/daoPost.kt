package com.foundmypet.RoomDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.foundmypet.RoomDatabase.Entities.Post

@Dao
interface IPostDao {

    @Query("SELECT * FROM post_table")
    fun getList(): List<Post>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book:Post)

    @Query("DELETE FROM post_table")
    suspend fun deleteAll()
}
