package com.foundmypet

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.domain.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_post.view.*

class PostListAdapter(val list: List<Post>): RecyclerView.Adapter<PostListAdapter.PostListViewHolder>() {
    class PostListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun render(post: Post){
            val picasso = Picasso.get()
            picasso.load(post.postUserImage).into(view.userImageView)

            view.usernameTextView.text = post.postUserName
            view.descriptionTextView.text = post.postTitle
            //Arreglar lo de abajo, la fecha de antiguedad se debe calcular
            view.commentAntiquityTextView.text = post.postDate

            view.setOnClickListener {

                val intent = Intent(view.context,PostPageActivity::class.java)
                intent.putExtra("iPostUserName", post.postUserName)
                intent.putExtra("iPostDate", post.postDate)
                intent.putExtra("iPostDescription", post.postTitle)
                intent.putExtra("iPostId", post.postId)
                intent.putExtra("iPostImage", post.postImage)
                intent.putExtra("iPostUserImage", post.postUserImage)

                view.context.startActivity(intent)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val viewInflater = LayoutInflater.from(parent.context)
        return PostListViewHolder(viewInflater.inflate(R.layout.row_post, parent,false))
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        holder.render(list[position])
    }

    override fun getItemCount(): Int = list.size
}