package com.foundmypet.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.data.PostsRepository
import com.e.domain.Post
import com.e.usescases.GetPosts
import com.foundmypet.PostListAdapter
import com.foundmypet.R
import com.foundmypet.viewModel.MainViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*
import edu.bo.framework.localData.PostLocalData

class HomeFragment : Fragment() {
    lateinit var mainViewModel: MainViewModel
    //FIREBASE---1
    private val listPosts: ArrayList<Post> = ArrayList()
    private val database = Firebase.database
    val myRef = database.getReference("posts")
    private lateinit var messagesListener: ValueEventListener

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //LAYOUT
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerViewMain.layoutManager = layoutManager

        //CASO DE USO
        val usesCases = GetPosts(PostsRepository(PostLocalData()))
        mainViewModel = MainViewModel(usesCases)
        mainViewModel.model.observe(this, Observer(::upadateUi))
        mainViewModel.loadPosts()

        //FIREBASE
//        messagesListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                listPosts.clear()
//                dataSnapshot.children.forEach { child ->
//                    val post: Post = Post(
//                        child.child("title").getValue<String>(),
//                        child.child("posterPath").getValue<String>())
//                    post.let { listPosts.add(it) }
//                }
//                mainViewModel = MainViewModel(usesCases)
//                mainViewModel.model.observe(this, Observer(::upadateUi))
//                mainViewModel.loadPosts()
//            }
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("TAG", "messages:onCancelled: ${error.message}")
//            }
//        }
//        myRef.addValueEventListener(messagesListener)
    }

    fun upadateUi(model: MainViewModel.UiModel) {
        when(model) {
            is MainViewModel.UiModel.Content -> showList(model.post)
        }
    }

    private fun showList(list: List<Post>) {
        recyclerViewMain.adapter = PostListAdapter(list)
    }
}