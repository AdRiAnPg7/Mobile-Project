package com.foundmypet.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.data.PostsRepository
import com.e.domain.Post
import com.e.usescases.GetPosts
import com.foundmypet.PostListAdapter
import com.foundmypet.R
import com.foundmypet.viewModel.MainViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*
import edu.bo.framework.localData.PostLocalData
import java.util.ArrayList

class HomeFragment : Fragment() {
    private var postListAdapter: PostListAdapter? = null
    private val postList: MutableList<Post> = ArrayList()
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //LAYOUT
        val layoutManager = LinearLayoutManager(context)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerViewMain.layoutManager = layoutManager

        //CASO DE USO FIREBASE
        //postList.clear()
        //setupRecyclerView(recyclerViewMain)

        //CASO DE USO
        val usesCases = GetPosts(PostsRepository(PostLocalData()))
        mainViewModel = MainViewModel(usesCases)
        mainViewModel.model.observe(this, Observer(::upadateUi))
        mainViewModel.loadPosts()
    }

    private fun setupRecyclerView(recyclerViewMain: RecyclerView) {
        val postsRef = FirebaseDatabase.getInstance().reference.child("Posts")

        postsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                postList.clear()
                snapshot.children.forEach { child ->
                    val post: Post? =
                        Post(child.child("title").getValue<String>(),
                            child.child("postImage").getValue<String>(),
                            child.child("postDate").getValue<String>(),
                            child.child("petName").getValue<String>(),
                            child.child("petSpecies").getValue<String>(),
                            child.child("petRace").getValue<String>(),
                            child.child("petColor").getValue<String>(),
                            child.child("phoneNumber").getValue<String>(),
                            child.child("postDescription").getValue<String>(),
                            child.child("postPublisher").getValue<String>(),
                            child.child("postUserImage").getValue<String>(),
                            child.child("postUserName").getValue<String>())
                    //val post = snapshot.getValue(Post::class.java)
                    post?.let { postList.add(it) }
                }
                recyclerViewMain.adapter = PostListAdapter(postList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
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