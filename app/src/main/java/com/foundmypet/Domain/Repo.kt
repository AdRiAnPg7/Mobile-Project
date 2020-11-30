package com.foundmypet.Domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foundmypet.Post
import com.google.firebase.firestore.FirebaseFirestore

class Repo {
    fun getPostData():LiveData<MutableList<Post>>{
        val mutableData = MutableLiveData<MutableList<Post>>()

        FirebaseFirestore.getInstance().collection("Posts").get().addOnSuccessListener { result ->
            val listData: MutableList<Post> = mutableListOf<Post>()
            for(document in result){
                val postId = document.getString("postId")
                Log.d("IDPOST",postId.toString())
                val postDescription = document.getString("postDescription")
                val postImage = document.getString("postImage")
                val postUserImage = document.getString("postUserImage")
                val postUserName = document.getString("postUserName")
                val postDate = document.getString("postDate")
                val post = Post(postId!!,postDescription!!,postImage!!,postUserImage!!,postUserName!!,postDate!!)
                listData.add(post)
            }
            mutableData.value = listData
        }
        return mutableData
    }
}