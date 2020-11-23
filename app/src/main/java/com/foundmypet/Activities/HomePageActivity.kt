package com.foundmypet


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home_page.*
enum class ProviderType{
    BASIC
}
class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        setSupportActionBar(toolbar)
        setConfigDrawer()

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
        recylcerView.adapter = PostListAdapter(lista, applicationContext)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recylcerView.layoutManager = linearLayoutManager

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