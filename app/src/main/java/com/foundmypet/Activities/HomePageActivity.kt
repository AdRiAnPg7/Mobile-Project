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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.foundmypet.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_home_page.*


enum class ProviderType{
    BASIC
}
class HomePageActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val addPostFragment = AddPostFragment()


    private lateinit var adapter: PostListAdapter

    private  val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

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


        //=================== JUANJO=====
        /*recylcerView.layoutManager = LinearLayoutManager(this)
        recylcerView.adapter = adapter*/


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




