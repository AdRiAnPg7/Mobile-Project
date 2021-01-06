package com.foundmypet


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.foundmypet.Fragments.AddPostFragment
import com.foundmypet.Fragments.HomeFragment
import com.foundmypet.Fragments.LostPetsFrament
import com.foundmypet.Fragments.SearchFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home_page.*


enum class ProviderType{
    BASIC
}
class HomePageActivity : AppCompatActivity() {
    // Vars Fragments
    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val addPostFragment = AddPostFragment()
    private val lostPetFragment = LostPetsFrament()

    //Vars Auth


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
                R.id.sidebar_end_session_item -> {
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this, MainActivity::class.java))
                }
                R.id.sidebar_lost_pets_item ->  replaceFragment(lostPetFragment)
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

        // Setup
        intent.getStringExtra("email")?.let { Log.d("CORREO", it) }
        val email = intent.getStringExtra("email")
        Log.d("CORREO", email?:"Null")
        setup(email ?:"Sin Correo")

    }

    // Setup
    private fun setup(email:String){
        val nav =  findViewById<NavigationView>(R.id.drawer_navigation_view)
        val headerView  = nav.getHeaderView(0);
        val textChange = headerView.findViewById<TextView>(R.id.header_email_text)
        textChange.text = email
        //header_email_text.text = email
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




