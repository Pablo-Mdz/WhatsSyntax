package com.syntax_institut.whatssyntax.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.whatssyntax.data.model.Chat
import com.syntax_institut.whatssyntax.data.model.Message
import com.syntax_institut.whatssyntax.databinding.MessageChatReciverBinding
import com.syntax_institut.whatssyntax.databinding.MessageChatSenderBinding

class ChatDetailAdapter(
    private val dataset: MutableList<Message>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val message_send = 1
    private val message_reciver = 2

    inner class SentMessageHolder(val binding: MessageChatSenderBinding) : RecyclerView.ViewHolder(binding.root)
    inner class ReceivedMessageHolder(val binding: MessageChatReciverBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        val message = dataset[position]
        return if (message.incoming) {
            message_send
        } else {
            message_reciver
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //  inflate the sent message Layout if viewtype is message_send
        return if (viewType == message_send) {
            val binding = MessageChatSenderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            SentMessageHolder(binding)
            // inflate the received message Layout if viewtype is message_reciver
        } else {
            val binding = MessageChatReciverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ReceivedMessageHolder(binding)
        }
    }

   override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val message = dataset[position]
       //check if the viewholder is an instance of the sent message or reciver holder
    if (holder.itemViewType == message_reciver) {
        (holder as ReceivedMessageHolder).binding.tvMessageReciver.text = message.text
    } else {
        (holder as SentMessageHolder).binding.tvMessageSender.text = message.text
    }

}

    override fun getItemCount() :Int {
        return dataset.size
    }

        // add new message to the dataset
    fun addMessage(message: Message) {
        dataset.add(message)
        notifyItemInserted(dataset.size - 1)
    }
}

