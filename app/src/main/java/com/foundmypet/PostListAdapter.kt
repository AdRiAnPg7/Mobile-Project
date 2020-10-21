package com.foundmypet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_post.view.*

class PostListAdapter(private var list: ArrayList<Post>, applicationContext: Context?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class PostListViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_post, parent, false)
        return PostListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = this.list[position]
        holder.itemView.usernameTextView.text = post.postUserName
        holder.itemView.descriptionTextView.text = post.postDescription
        holder.itemView.commentAntiquityTextView.text = post.postDate
    }


}
