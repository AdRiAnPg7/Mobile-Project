package com.foundmypet.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.foundmypet.CreateLostPetPostActivity
import com.foundmypet.LoginActivity
import com.foundmypet.MainActivity
import com.foundmypet.R
import kotlinx.android.synthetic.main.fragment_add_post.*


class AddPostFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_type_post_found_pet.setOnClickListener{
            startActivity(Intent(context,CreateLostPetPostActivity::class.java))
        }
    }

}