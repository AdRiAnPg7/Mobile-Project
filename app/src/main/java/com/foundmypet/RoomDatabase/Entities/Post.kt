package com.foundmypet.RoomDatabase.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
class Post(@ColumnInfo (name = "userID") var userID: String,
           @ColumnInfo (name = "postComment") var postComment: String,
           @ColumnInfo (name = "postImageUrl") var postImageUrl: String,
           @ColumnInfo (name = "postDate") var postDate: String,

    //provisional
           @ColumnInfo (name = "userImageUrl") var userImageUrl: String,
           @ColumnInfo (name = "userName") var userName: String) {
            @PrimaryKey(autoGenerate = true)
            @ColumnInfo(name = "postId")
            var id: Long = 0
            }

