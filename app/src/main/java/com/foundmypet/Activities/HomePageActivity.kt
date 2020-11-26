package com.foundmypet


import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.foundmypet.RoomDatabase.Database.AppRoomDatabase
import com.foundmypet.RoomDatabase.Entities.Post
import com.foundmypet.RoomDatabase.Repositories.PostRepository
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


enum class ProviderType{
    BASIC
}
class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        setSupportActionBar(toolbar)
        setConfigDrawer()



        //set icon
        //supportActionBar?.setHomeButtonEnabled(false)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setDisplayShowHomeEnabled(false)
        //supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_search)

        GlobalScope.launch {
            val postDao = AppRoomDatabase.getDatabase(applicationContext).postdato()
            val repository = PostRepository(postDao)
            //repository.insert(com.foundmypet.RoomDatabase.Entities.Post("the" , "Perdi mi perro aiuda", "url", "4 hr", "url", "Augusto"))
            val listaPost = repository.getListBooks()
            recylcerView.adapter = PostListAdapter(listaPost as ArrayList<Post>, applicationContext)
            val linearLayoutManager = LinearLayoutManager(applicationContext)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            recylcerView.layoutManager = linearLayoutManager
        }




    }

    fun setConfigDrawer() {
        var drawerToggle = ActionBarDrawerToggle(
            this,
            main_drawer_layout,
            toolbar,
            0, 0
        )
        main_drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

    }
}