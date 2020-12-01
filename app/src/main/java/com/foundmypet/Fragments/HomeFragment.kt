package com.foundmypet.Fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.foundmypet.Post
import com.foundmypet.PostListAdapter
import com.foundmypet.R
import com.foundmypet.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_post_page.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.row_post.*

class HomeFragment : Fragment() {

    // Vars
    //private  val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
    private lateinit var adapter: PostListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //INIT ADAPTER
        adapter = PostListAdapter(context)
        Log.d("IDPOST","AQUI DESPUES DEL ADAPTER")
        recyclerViewMain.layoutManager = LinearLayoutManager(context)
        recyclerViewMain.adapter = adapter
        Log.d("IDPOST","AQUI SUPER DESPUES DEL ADAPTER")
        //POST LIST
        val list: MutableList<Post> = mutableListOf<Post>()
        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://www.lavanguardia.com/r/GODO/LV/p5/WebSite/2018/07/25/Recortada/img_econcejo_20180730-165058_imagenes_lv_terceros_istock-894361286-k0hH--656x438@LaVanguardia-Web.jpg","Alguien llamado x","5/5/2020"))
        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://www.lavanguardia.com/r/GODO/LV/p5/WebSite/2018/07/25/Recortada/img_econcejo_20180730-165058_imagenes_lv_terceros_istock-894361286-k0hH--656x438@LaVanguardia-Web.jpg","Alguien llamado x","5/5/2020"))
        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://i.pinimg.com/736x/5f/49/e1/5f49e11efad480bf5be95e2cd30332f4.jpg","Alguien llamado x","5/5/2020"))
        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://i.pinimg.com/736x/5f/49/e1/5f49e11efad480bf5be95e2cd30332f4.jpg","Alguien llamado x","5/5/2020"))
        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://i.pinimg.com/736x/5f/49/e1/5f49e11efad480bf5be95e2cd30332f4.jpg","Alguien llamado x","5/5/2020"))
        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://i.pinimg.com/736x/5f/49/e1/5f49e11efad480bf5be95e2cd30332f4.jpg","Alguien llamado x","5/5/2020"))
        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://www.seoptimer.com/es/blog/wp-content/uploads/2016/05/foto-de-perfil-adecuada.jpg","Alguien llamado x","5/5/2020"))
        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://www.seoptimer.com/es/blog/wp-content/uploads/2016/05/foto-de-perfil-adecuada.jpg","Alguien llamado x","5/5/2020"))
        list.add(Post("1","Busco a mi mascotita plox","un link generico","https://www.seoptimer.com/es/blog/wp-content/uploads/2016/05/foto-de-perfil-adecuada.jpg","Alguien llamado x","5/5/2020"))

        //ADAPTER
        adapter.setListData(list)
        adapter.notifyDataSetChanged()


        Log.d("IDPOST","ANTES DEL OOBSERVEDATA")
        //observeData()
        Log.d("IDPOST","DESPUES")
        //LAYOUT
//        recyclerViewMain.adapter = PostListAdapter(lista, context)
//        val linearLayoutManager = LinearLayoutManager(context)
//        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//        recyclerViewMain.layoutManager = linearLayoutManager
    }

//    fun observeData(){
//        viewModel.fetchPostData().observe(this, Observer {
//            adapter.setListData(it)
//            adapter.notifyDataSetChanged()
//        })
//    }
}