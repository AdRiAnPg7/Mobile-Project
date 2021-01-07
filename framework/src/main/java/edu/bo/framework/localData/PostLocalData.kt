package edu.bo.framework.localData

import com.e.data.IRemoteDataSource
import com.e.domain.Post

class PostLocalData():IRemoteDataSource {
    override fun getAllPosts(): List<Post> {
        val list: List<Post> = listOf(
            Post("1","Busco a mi mascotita plox","un link generico","https://www.lavanguardia.com/r/GODO/LV/p5/WebSite/2018/07/25/Recortada/img_econcejo_20180730-165058_imagenes_lv_terceros_istock-894361286-k0hH--656x438@LaVanguardia-Web.jpg","Alguien llamado x","5/5/2020"),
            Post("1","Busco a mi mascotita plox","un link generico","https://www.lavanguardia.com/r/GODO/LV/p5/WebSite/2018/07/25/Recortada/img_econcejo_20180730-165058_imagenes_lv_terceros_istock-894361286-k0hH--656x438@LaVanguardia-Web.jpg","Alguien llamado x","5/5/2020"),
            Post("3","Busco a mi mascotita plox","un link generico","https://i.pinimg.com/736x/5f/49/e1/5f49e11efad480bf5be95e2cd30332f4.jpg","Alguien llamado x","5/5/2020"),
            Post("1","Busco a mi mascotita plox","un link generico","https://i.pinimg.com/736x/5f/49/e1/5f49e11efad480bf5be95e2cd30332f4.jpg","Alguien llamado x","5/5/2020"),
            Post("1","Busco a mi mascotita plox","un link generico","https://i.pinimg.com/736x/5f/49/e1/5f49e11efad480bf5be95e2cd30332f4.jpg","Alguien llamado x","5/5/2020"),
            Post("1","Busco a mi mascotita plox","un link generico","https://i.pinimg.com/736x/5f/49/e1/5f49e11efad480bf5be95e2cd30332f4.jpg","Alguien llamado x","5/5/2020"),
            Post("1","Busco a mi mascotita plox","un link generico","https://www.seoptimer.com/es/blog/wp-content/uploads/2016/05/foto-de-perfil-adecuada.jpg","Alguien llamado x","5/5/2020"),
            Post("1","Busco a mi mascotita plox","un link generico","https://www.seoptimer.com/es/blog/wp-content/uploads/2016/05/foto-de-perfil-adecuada.jpg","Alguien llamado x","5/5/2020"),
            Post("1","Busco a mi mascotita plox","un link generico","https://www.seoptimer.com/es/blog/wp-content/uploads/2016/05/foto-de-perfil-adecuada.jpg","Alguien llamado x","5/5/2020")
        )
        return list
    }

    override fun addPost(post: Post) {
        TODO("Not yet implemented")
    }
}