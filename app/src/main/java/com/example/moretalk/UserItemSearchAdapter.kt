package com.example.moretalk

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.moretalk.databinding.UserItemSearchLayoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class UserItemSearchAdapter(
    val user: ArrayList<User>
): RecyclerView.Adapter<UserItemSearchAdapter.UserItemSearchViewHolder>() {

    private var firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    class UserItemSearchViewHolder(val binding: UserItemSearchLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemSearchViewHolder {
        return UserItemSearchViewHolder(UserItemSearchLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: UserItemSearchViewHolder, position: Int) {
        val currentUser = user[position]
        holder.binding.textUserName.text = currentUser.userName
        holder.binding.textUserProfileName.text = currentUser.name
        Picasso.get()
            .load(currentUser.imageUrl)
            .placeholder(R.drawable.profile)
            .into(holder.binding.imageUserPhotoSearch)

        checkFollowingStatus(currentUser.uid, holder.binding.buttonFollow)

        holder.binding.buttonFollow.setOnClickListener {
            if (holder.binding.buttonFollow.text.toString() == "Follow"){
                firebaseUser?.uid.let { it1 ->
                    FirebaseDatabase.getInstance().getReference()
                        .child("Follow")
                        .child(it1.toString())
                        .child("Following")
                        .child(currentUser.uid!!)
                        .setValue(true)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful){
                                FirebaseDatabase.getInstance().getReference()
                                    .child("Follow")
                                    .child(currentUser.uid!!)
                                    .child("Followers")
                                    .child(it1.toString())
                                    .setValue(true)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful){

                                        }
                                    }
                            }
                        }
                }
            }else{
                firebaseUser?.uid.let { it1 ->
                    FirebaseDatabase.getInstance().getReference()
                        .child("Follow")
                        .child(it1.toString())
                        .child("Following")
                        .child(currentUser.uid!!)
                        .removeValue()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful){
                                FirebaseDatabase.getInstance().getReference()
                                    .child("Follow")
                                    .child(currentUser.uid!!)
                                    .child("Followers")
                                    .child(it1.toString())
                                    .removeValue()
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful){

                                        }
                                    }
                            }
                        }
                }
            }
        }


//        holder.itemView.setOnClickListener{
//            val navController = holder.itemView.findNavController()
//
//            val action = ChatFragmentDirections.actionChatFragmentToConversationFragment(currentUser.name!!, currentUser.uid!!)
//            navController.navigate(action)
//        }
    }

    private fun checkFollowingStatus(uid: String?, buttonFollow: AppCompatButton) {
        val followingRef = firebaseUser?.uid.let { it1 ->
            FirebaseDatabase.getInstance().getReference()
                .child("Follow")
                .child(it1.toString())
                .child("Following")
        }
        followingRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.child(uid!!).exists()){
                    buttonFollow.text = "Following"
                }else{
                    buttonFollow.text = "Follow"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun getItemCount(): Int {
        return user.size
    }
}