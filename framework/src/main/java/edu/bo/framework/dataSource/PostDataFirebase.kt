package edu.bo.framework.dataSource

import com.e.data.IRemoteDataSource
import com.e.domain.Post
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PostDataFirebase: IRemoteDataSource{
    private val database = Firebase.database
    private val myRef = database.getReference("posts")

    override fun getAllPosts(): List<Post> {
        TODO("Not yet implemented")
    }

    override fun addPost(post: Post) {
        myRef.child(myRef.push().key.toString()).setValue(post)
    }
}