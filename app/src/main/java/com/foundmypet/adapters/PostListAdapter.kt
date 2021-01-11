package com.foundmypet

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.domain.Post
import com.foundmypet.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.row_post.view.*

class PostListAdapter(private val list: List<Post>): RecyclerView.Adapter<PostListAdapter.PostListViewHolder>() {
    private var firebaseUser: FirebaseUser? = null

    class PostListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun render(post: Post){
            Picasso.get().load(post.postImage).into(view.postImageView)
            view.descriptionTextView.text = post.postDescription
            view.commentAntiquityTextView.text = post.postDate

            //publisherInfo(view.userImageView, view.usernameTextView, post.postPublisher)
            Picasso.get().load(post.postUserImage).into(view.userImageView)
            view.usernameTextView.text = post.postUserName

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

        private fun publisherInfo(userImageView: CircleImageView, usernameTextView: TextView, postPublisher: String) {
            val usersRef = FirebaseDatabase.getInstance().reference.child("Users").child(postPublisher)

            usersRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        val user = snapshot.getValue<User>(User::class.java)
                        Picasso.get().load(user!!.getImage()).into(userImageView)
                        usernameTextView.text = user!!.getUser()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val viewInflater = LayoutInflater.from(parent.context)
        return PostListViewHolder(viewInflater.inflate(R.layout.row_post, parent,false))
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        firebaseUser = FirebaseAuth.getInstance().currentUser
        holder.render(list[position])
    }

    override fun getItemCount(): Int = list.size
}