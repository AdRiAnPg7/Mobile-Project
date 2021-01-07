package com.foundmypet

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.e.data.PostsRepository
import com.e.domain.Post
import com.e.usescases.AddPost
import edu.bo.framework.dataSource.PostDataFirebase
import kotlinx.android.synthetic.main.activity_create_lost_pet_post.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateLostPetPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_lost_pet_post)
        savePost()
    }

    private fun savePost(){
        btn_create_post.setOnClickListener {
            val title = text_input_lost_pet_post_tittle.text
            val name = input_create_lost_pet_post_name.text
            val species = input_create_lost_pet_post_species.text
            val race = input_create_lost_pet_post_race.text
            val color = input_create_lost_pet_post_color.text
            val lastSeen =  input_create_lost_pet_post_lastSeen.text
            val phoneNumber = input_create_lost_pet_post_phoneNumber.text
            val urlPostImage = "https://www.fundacion-affinity.org/sites/default/files/que-hacer-si-encuentras-a-un-perro-perdido-o-abandonado.jpg"
            val urlUserImage = "https://scontent.fcbb1-2.fna.fbcdn.net/v/t1.0-9/30124452_1621236777963067_6266752421855232000_o.jpg?_nc_cat=101&ccb=2&_nc_sid=09cbfe&_nc_ohc=Va5rYElCXAYAX9gyrFL&_nc_ht=scontent.fcbb1-2.fna&oh=1147f4a31d834295c677435a03075fee&oe=601AE341"
            val newPost = Post(
                "1",
                title.toString(),
                urlPostImage.toString(),
                urlUserImage.toString(),
                lastSeen.toString(),
                "Melman",
                name.toString(),
                species.toString(),
                race.toString(),
                color.toString(),
                phoneNumber.toString())

            //USES CASE
            val usesCase = AddPost(PostsRepository(PostDataFirebase()), newPost)
            usesCase.invoke()

            Toast.makeText(this,"Se añadio un Post", Toast.LENGTH_SHORT).show()
            clearTexts()
        }
    }
    private fun clearTexts(){
        text_input_lost_pet_post_tittle.text.clear()
        input_create_lost_pet_post_name.text.clear()
        input_create_lost_pet_post_species.text.clear()
        input_create_lost_pet_post_race.text.clear()
        input_create_lost_pet_post_color.text.clear()
        input_create_lost_pet_post_lastSeen.text.clear()
        input_create_lost_pet_post_phoneNumber.text.clear()
    }
}

