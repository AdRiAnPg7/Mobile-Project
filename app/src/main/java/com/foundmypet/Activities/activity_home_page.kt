package com.foundmypet


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home_page.*
enum class ProviderType{
    BASIC
}
class activity_home_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val lista = arrayListOf<Post>()
        lista.add(Post("1", "Busco a mi mascotita plox", "un link generico", "mi link de imagen", "Alguien llamado x" , "5/5/2020"))
        lista.add(Post("1", "Busco a mi mascotita plox", "un link generico", "mi link de imagen", "Alguien llamado x" , "5/5/2020"))
        lista.add(Post("1", "Busco a mi mascotita plox", "un link generico", "mi link de imagen", "Alguien llamado x" , "5/5/2020"))
        lista.add(Post("1", "Busco a mi mascotita plox", "un link generico", "mi link de imagen", "Alguien llamado x" , "5/5/2020"))
        lista.add(Post("1", "Busco a mi mascotita plox", "un link generico", "mi link de imagen", "Alguien llamado x" , "5/5/2020"))
        lista.add(Post("1", "Busco a mi mascotita plox", "un link generico", "mi link de imagen", "Alguien llamado x" , "5/5/2020"))
        lista.add(Post("1", "Busco a mi mascotita plox", "un link generico", "mi link de imagen", "Alguien llamado x" , "5/5/2020"))
        lista.add(Post("1", "Busco a mi mascotita plox", "un link generico", "mi link de imagen", "Alguien llamado x" , "5/5/2020"))
        lista.add(Post("1", "Busco a mi mascotita plox", "un link generico", "mi link de imagen", "Alguien llamado x" , "5/5/2020"))
        lista.add(Post("1", "Busco a mi mascotita plox", "un link generico", "mi link de imagen", "Alguien llamado x" , "5/5/2020"))
        lista.add(Post("1", "Busco a mi mascotita plox", "un link generico", "mi link de imagen", "Alguien llamado x" , "5/5/2020"))
        lista.add(Post("1", "Busco a mi mascotita plox", "un link generico", "mi link de imagen", "Alguien llamado x" , "5/5/2020"))
        lista.add(Post("1", "Busco a mi mascotita plox", "un link generico", "mi link de imagen", "Alguien llamado x" , "5/5/2020"))

        recylcerView.adapter = PostListAdapter(lista, applicationContext)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recylcerView.layoutManager = linearLayoutManager

    }
}