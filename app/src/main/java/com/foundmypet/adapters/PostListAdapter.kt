package com.foundmypet

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.domain.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_post_page.*
import kotlinx.android.synthetic.main.row_post.view.*

class PostListAdapter(val list: List<Post>): RecyclerView.Adapter<PostListAdapter.PostListViewHolder>() {
    class PostListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun render(post: Post){
            Picasso.get().load(post.postUserImage).into(view.userImageView)
            Picasso.get().load(post.postImage).into(view.postImageView)
            view.usernameTextView.text = post.postUserName
            view.descriptionTextView.text = post.postDescription
            view.commentAntiquityTextView.text = post.postDate

            view.setOnClickListener {
                val intent = Intent(view.context,PostPageActivity::class.java)
                intent.putExtra("iPostTittle", post.postTittle)
                intent.putExtra("iPostImage", post.postImage)
                intent.putExtra("iPostUserImage", post.postUserImage)
                intent.putExtra("iPostUserName", post.postUserName)
                intent.putExtra("iPostDate", post.postDate)
                intent.putExtra("iPetName", post.petName)
                intent.putExtra("iPetSpecies", post.petSpecies)
                intent.putExtra("iPetRace", post.petRace)
                intent.putExtra("iPetColor", post.petColor)
                intent.putExtra("iPhoneNumber", post.phoneNumber)
                intent.putExtra("iPostDescription", post.postDescription)
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