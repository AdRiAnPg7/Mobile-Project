package com.foundmypet


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.foundmypet.Fragments.AddPostFragment
import com.foundmypet.Fragments.HomeFragment
import com.foundmypet.Fragments.SearchFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home_page.*


enum class ProviderType{
    BASIC
}
class HomePageActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val addPostFragment = AddPostFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)


        //TOOLBAR
        setSupportActionBar(toolbar)
        setConfigDrawer()

        // DRAWER NAVIGATION
        drawer_navigation_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.sidebar_edit_post_item -> startActivity(Intent(this, UserProfileActivity::class.java))
            }
            true
        }


        //BOTTOM NAVIGATION FRAGMENTS
        replaceFragment(homeFragment)
        bottom_navigation.selectedItemId = R.id.home
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(homeFragment)
                R.id.addPost -> replaceFragment(addPostFragment)
                R.id.search -> replaceFragment(searchFragment)
            }
            true
        }

        //set icon
        //supportActionBar?.setHomeButtonEnabled(false)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setDisplayShowHomeEnabled(false)
        //supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_search)
    }

    // FUNTIONS FOR TOOLBAR
    private fun setConfigDrawer() {
        var drawerToggle = ActionBarDrawerToggle(
            this,
            main_drawer_layout,
            toolbar,
            0, 0
        )
        main_drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    // FUNTIONS FOR BOTTOM NAVIGATION
    private fun replaceFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment)
            commit()
        }


}


