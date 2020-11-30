package com.foundmypet.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foundmypet.Domain.Repo
import com.foundmypet.Post

class MainViewModel: ViewModel() {
    private val repo = Repo()
    fun fetchPostData(): LiveData<MutableList<Post>> {
        val mutableData = MutableLiveData<MutableList<Post>>()
        repo.getPostData().observeForever{postList ->
            mutableData.value = postList
        }
        return mutableData
    }
}