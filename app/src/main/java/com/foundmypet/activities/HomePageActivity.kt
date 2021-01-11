package com.foundmypet


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.foundmypet.Model.User
import com.foundmypet.fragments.AddPostFragment
import com.foundmypet.fragments.HomeFragment
import com.foundmypet.fragments.LostPetsFrament
import com.foundmypet.fragments.SearchFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.sidebar_header.*


enum class ProviderType{
    BASIC
}
class HomePageActivity : AppCompatActivity() {
    // Vars Fragments
    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val addPostFragment = AddPostFragment()
    private val lostPetFragment = LostPetsFrament()

    // USER VARS
    private lateinit var firebaseUser: FirebaseUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        // USER
        firebaseUser = FirebaseAuth.getInstance().currentUser!!

        // GET USER DATA
        getUserData()

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
//        intent.getStringExtra("email")?.let { Log.d("CORREO", it) }
//        val email = intent.getStringExtra("email")
//        Log.d("CORREO", email?:"Null")
//        setup()

    }

    private fun getUserData() {
        val currentUser = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUser.uid)
        currentUser.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val user = snapshot.getValue<User>(User::class.java)
                    Picasso.get().load(user!!.getImage()).into(header_profile_picture)
                    header_username_text.text = user!!.getUser()
                    header_email_text.text = user!!.getEmail()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


    // Setup
//    private fun setup(){
//        val nav =  findViewById<NavigationView>(R.id.drawer_navigation_view)
//        val headerView  = nav.getHeaderView(0);
//        val textChange = headerView.findViewById<TextView>(R.id.header_email_text)
//        textChange.text
//        //header_email_text.text = email
//    }


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




