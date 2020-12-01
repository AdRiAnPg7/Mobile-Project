package com.foundmypet.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foundmypet.Domain.Repo
import com.e.domain.Post

class MainViewModel: ViewModel() {
    private val repo = Repo()

    fun fetchPostData(): LiveData<MutableList<Post>> {
        Log.d("IDPOST","Antesaaaaaaaaaaa")
        val mutableData = MutableLiveData<MutableList<Post>>()
        Log.d("IDPOST",mutableData.toString())
        repo.getPostData().observeForever { postList ->
            Log.d("IDPOST","222222222222222")
            mutableData.value = postList
            Log.d("IDPOST","33333333333333333333")

        }
        Log.d("IDPOST",mutableData.toString()+" DESPUES ")
        return mutableData
    }
}