package com.foundmypet.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.foundmypet.Model.User
import com.foundmypet.R
import com.squareup.picasso.Picasso

class UserAdapter  (
    private var mContext: Context,
    private var mUser:List<User>,
    private var isFragment: Boolean = false) : RecyclerView.Adapter<UserAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.activity_user_profile, parent, false)
        return UserAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mUser.size
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val user = mUser[position]
        holder.userNameTextView.text = user.getUser()
        holder.userEmailTextView.text = user.getEmail()
        Picasso.get().load(user.getImage()).placeholder(R.drawable.add_image_profile).into(holder.userImage)
    }

    class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView){
        var userNameTextView: TextView = itemView.findViewById(R.id.header_username_text)
        var userEmailTextView: TextView = itemView.findViewById(R.id.text_email_address)
        var userImage : ImageView = itemView.findViewById(R.id.image_user_profile)
    }
}