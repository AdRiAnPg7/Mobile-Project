package com.foundmypet.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.foundmypet.Post
import com.foundmypet.PostListAdapter
import com.foundmypet.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lista = arrayListOf<Post>()
        lista.add(
            Post(
                "1",
                "Busco a mi mascotita plox",
                "un link generico",
                "mi link de imagen",
                "Alguien llamado x",
                "5/5/2020"
            )
        )
        lista.add(
            Post(
                "1",
                "Busco a mi mascotita plox",
                "un link generico",
                "mi link de imagen",
                "Alguien llamado x",
                "5/5/2020"
            )
        )
        lista.add(
            Post(
                "1",
                "Busco a mi mascotita plox",
                "un link generico",
                "mi link de imagen",
                "Alguien llamado x",
                "5/5/2020"
            )
        )
        lista.add(
            Post(
                "1",
                "Busco a mi mascotita plox",
                "un link generico",
                "mi link de imagen",
                "Alguien llamado x",
                "5/5/2020"
            )
        )
        lista.add(
            Post(
                "1",
                "Busco a mi mascotita plox",
                "un link generico",
                "mi link de imagen",
                "Alguien llamado x",
                "5/5/2020"
            )
        )
        lista.add(
            Post(
                "1",
                "Busco a mi mascotita plox",
                "un link generico",
                "mi link de imagen",
                "Alguien llamado x",
                "5/5/2020"
            )
        )

        //LAYOUT
        recyclerViewMain.adapter = PostListAdapter(lista, context)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerViewMain.layoutManager = linearLayoutManager
    }
}