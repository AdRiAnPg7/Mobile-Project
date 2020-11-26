package com.foundmypet.RoomDatabase.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.foundmypet.RoomDatabase.Entities.Post
import com.foundmypet.RoomDatabase.dao.IPostDao

@Database(entities = arrayOf(Post::class), version = 1, exportSchema = false)
abstract class AppRoomDatabase: RoomDatabase() {
    abstract fun postdato(): IPostDao

    companion object {
        private var INSTANCE : AppRoomDatabase? = null

        fun getDatabase(context: Context) : AppRoomDatabase {
            val tempInstance = INSTANCE
            if ( tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppRoomDatabase::class.java, "db_name").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
