package com.foundmypet.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.domain.Post
import com.e.usescases.GetPosts
import kotlinx.coroutines.launch

class MainViewModel(private val listPosts: GetPosts): ScopedViewModel() {
    init {
        initScope()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() = _model

    sealed class UiModel {
        class Content(val post: List<Post>) : UiModel()
    }

    fun loadPosts(){
        launch {
            _model.value = UiModel.Content(listPosts.invoke())
        }
    }
}