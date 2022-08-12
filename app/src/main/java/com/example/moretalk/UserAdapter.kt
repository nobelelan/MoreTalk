package com.example.moretalk

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moretalk.databinding.UserLayoutBinding
import com.google.firebase.auth.FirebaseAuth

class UserAdapter(
    val userList: ArrayList<User>
): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(val binding: UserLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(UserLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.binding.txtUserName.text = currentUser.name

        holder.itemView.setOnClickListener{
            val navController = holder.itemView.findNavController()

            val action = ChatFragmentDirections.actionChatFragmentToConversationFragment(currentUser.name!!, currentUser.uid!!)
            navController.navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}