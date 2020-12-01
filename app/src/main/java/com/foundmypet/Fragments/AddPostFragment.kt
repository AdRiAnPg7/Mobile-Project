package com.foundmypet.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.foundmypet.R

class AddPostFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        selectTypePost()
        return inflater.inflate(R.layout.fragment_add_post, container, false)
    }

    private fun selectTypePost() {
        val buttonFindPet = view?.findViewById<Button>(R.id.button_type_post_found_pet)
        val buttonLostPet = view?.findViewById<Button>(R.id.button_type_post_lost_pet)

        buttonFindPet?.setOnClickListener {

        }

        buttonLostPet?.setOnClickListener {

        }

    }
}