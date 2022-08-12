package com.example.moretalk

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moretalk.databinding.ReceivedMessageBinding
import com.example.moretalk.databinding.SentMessageBinding
import com.example.moretalk.databinding.UserLayoutBinding
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(
    val messageList: ArrayList<Message>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVE = 1
    val ITEM_SENT = 2

    class SentViewHolder(val binding: SentMessageBinding): RecyclerView.ViewHolder(binding.root)
    class ReceivedViewHolder(val binding: ReceivedMessageBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1){
            return ReceivedViewHolder(ReceivedMessageBinding.inflate(LayoutInflater.from(parent.context),parent, false))
        }else{
            return SentViewHolder(SentMessageBinding.inflate(LayoutInflater.from(parent.context),parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMessage = messageList[position]

        if (holder.javaClass == SentViewHolder::class.java){
            val viewHolder = holder as SentViewHolder
            holder.binding.tvSentMessage.text = currentMessage.message
        }else{
            val viewHolder = holder as ReceivedViewHolder
            holder.binding.tvReceivedMessage.text = currentMessage.message
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun getItemViewType(position: Int): Int {

        val currentMessage = messageList[position]

        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return ITEM_SENT
        }else{
            return ITEM_RECEIVE
        }
    }
}