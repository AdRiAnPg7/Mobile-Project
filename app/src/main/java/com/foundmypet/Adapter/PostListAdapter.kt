package com.foundmypet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_post.view.*
import kotlin.coroutines.coroutineContext

class PostListAdapter(private val context: Context?): RecyclerView.Adapter<PostListAdapter.PostListViewHolder>() {
    //    class PostListViewHolder(view: View): RecyclerView.ViewHolder(view)
    private var dataList = mutableListOf<Post>()

    fun setListData(data:MutableList<Post>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_post, parent, false)
        return PostListViewHolder(v)
    }

    override fun getItemCount(): Int {
//        return this.list.size
        return if(dataList.size > 0){
            dataList.size
        }else{
            0
        }
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
//        val post = this.list[position]
//        holder.itemView.usernameTextView.text = post.postUserName
//        holder.itemView.descriptionTextView.text = post.postDescription
//        holder.itemView.commentAntiquityTextView.text = post.postDate
        val post: Post = dataList[position]
        holder.bindView(post)

    }

    inner class PostListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindView(post:Post){
//            Glide.with(context).load(post.postUserImage).into(itemView.userImageView)
            val picasso = Picasso.get()
            picasso.load(post.postUserImage).into(itemView.userImageView)

            itemView.usernameTextView.text = post.postUserName
            itemView.descriptionTextView.text = post.postDescription
            itemView.commentAntiquityTextView.text = post.postDate
        }
    }


}