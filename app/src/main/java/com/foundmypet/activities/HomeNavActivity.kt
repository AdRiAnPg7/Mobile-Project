package com.foundmypet.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.foundmypet.fragments.AddPostFragment
import com.foundmypet.fragments.HomeFragment
import com.foundmypet.fragments.SearchFragment
import com.foundmypet.R
import kotlinx.android.synthetic.main.activity_home_nav.*

class HomeNavActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_nav)

        val homeFragment = HomeFragment()
        val addPostFragment = AddPostFragment()
        val searchFragment = SearchFragment()

        makeCurrentFragment(homeFragment)
        bottom_navigation.selectedItemId=R.id.home
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> makeCurrentFragment(homeFragment)
                R.id.addPost -> makeCurrentFragment(addPostFragment)
                R.id.search -> makeCurrentFragment(searchFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }
}