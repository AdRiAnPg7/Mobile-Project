package com.foundmypet


import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.foundmypet.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_home_page.*


enum class ProviderType{
    BASIC
}
class HomePageActivity : AppCompatActivity() {

    private lateinit var adapter: PostListAdapter

    private  val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

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


//        val lista = arrayListOf<Post>()
//        lista.add(
//            Post(
//                "1",
//                "Busco a mi mascotita plox",
//                "un link generico",
//                "mi link de imagen",
//                "Alguien llamado x",
//                "5/5/2020"
//            )
//        )
//        lista.add(
//            Post(
//                "1",
//                "Busco a mi mascotita plox",
//                "un link generico",
//                "mi link de imagen",
//                "Alguien llamado x",
//                "5/5/2020"
//            )
//        )
//        lista.add(
//            Post(
//                "1",
//                "Busco a mi mascotita plox",
//                "un link generico",
//                "mi link de imagen",
//                "Alguien llamado x",
//                "5/5/2020"
//            )
//        )
//        lista.add(
//            Post(
//                "1",
//                "Busco a mi mascotita plox",
//                "un link generico",
//                "mi link de imagen",
//                "Alguien llamado x",
//                "5/5/2020"
//            )
//        )
//        lista.add(
//            Post(
//                "1",
//                "Busco a mi mascotita plox",
//                "un link generico",
//                "mi link de imagen",
//                "Alguien llamado x",
//                "5/5/2020"
//            )
//        )
//        lista.add(
//            Post(
//                "1",
//                "Busco a mi mascotita plox",
//                "un link generico",
//                "mi link de imagen",
//                "Alguien llamado x",
//                "5/5/2020"
//            )
//        )
//        recylcerView.adapter = PostListAdapter(lista, applicationContext)
//        val linearLayoutManager = LinearLayoutManager(applicationContext)
//        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//        recylcerView.layoutManager = linearLayoutManager

        adapter = PostListAdapter(this)


        recylcerView.layoutManager = LinearLayoutManager(this)
        recylcerView.adapter = adapter


//        val list: MutableList<Post> = mutableListOf<Post>()
//        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://www.lavanguardia.com/r/GODO/LV/p5/WebSite/2018/07/25/Recortada/img_econcejo_20180730-165058_imagenes_lv_terceros_istock-894361286-k0hH--656x438@LaVanguardia-Web.jpg","Alguien llamado x","5/5/2020"))
//        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://www.lavanguardia.com/r/GODO/LV/p5/WebSite/2018/07/25/Recortada/img_econcejo_20180730-165058_imagenes_lv_terceros_istock-894361286-k0hH--656x438@LaVanguardia-Web.jpg","Alguien llamado x","5/5/2020"))
//        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://i.pinimg.com/736x/5f/49/e1/5f49e11efad480bf5be95e2cd30332f4.jpg","Alguien llamado x","5/5/2020"))
//        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://i.pinimg.com/736x/5f/49/e1/5f49e11efad480bf5be95e2cd30332f4.jpg","Alguien llamado x","5/5/2020"))
//        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://i.pinimg.com/736x/5f/49/e1/5f49e11efad480bf5be95e2cd30332f4.jpg","Alguien llamado x","5/5/2020"))
//        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://i.pinimg.com/736x/5f/49/e1/5f49e11efad480bf5be95e2cd30332f4.jpg","Alguien llamado x","5/5/2020"))
//        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://www.seoptimer.com/es/blog/wp-content/uploads/2016/05/foto-de-perfil-adecuada.jpg","Alguien llamado x","5/5/2020"))
//        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://www.seoptimer.com/es/blog/wp-content/uploads/2016/05/foto-de-perfil-adecuada.jpg","Alguien llamado x","5/5/2020"))
//        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://www.seoptimer.com/es/blog/wp-content/uploads/2016/05/foto-de-perfil-adecuada.jpg","Alguien llamado x","5/5/2020"))
//
//
//
//        adapter.setListData(list)
//        adapter.notifyDataSetChanged()

        observeData()

    }

    fun observeData(){
        viewModel.fetchPostData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
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