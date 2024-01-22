package com.syntax_institut.whatssyntax.adapter


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import  androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.whatssyntax.R
import com.syntax_institut.whatssyntax.data.model.Chat
import com.syntax_institut.whatssyntax.databinding.ChatListBinding
import com.syntax_institut.whatssyntax.ui.ChatDetailFragment
import com.syntax_institut.whatssyntax.ui.ChatFragment
import com.syntax_institut.whatssyntax.ui.ChatFragmentDirections
import com.syntax_institut.whatssyntax.ui.StatusFragmentDirections


/**
 * 1. Create the adapter
 * 2. Create the viewholder
 * 3. Bind the viewholder
 * 4. call the data from Chat Class
 */
class ChatAdapter(
    private val dataset: List<Chat>,
    navController: NavController,
) : RecyclerView.Adapter<ChatAdapter.ItemViewholder>(){

    inner class ItemViewholder(val binding: ChatListBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * 1. Create the viewholder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ItemViewholder {
        val binding = ChatListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewholder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {

        /**
         * 1. Get the item from the dataset
         * 2. Bind the item to the viewholder
         */
        val item = dataset[position]
        // set all the data to the view
        holder.binding.tvName.text = item.contact.name
        holder.binding.tvMessage.text = item.messages.first().text
        holder.binding.imgProfileDetail.setImageResource(item.contact.image)

        // set the click listener to navigate to chat detail fragment
        holder.binding.chatCard.setOnClickListener {
            val bundle = Bundle()
            holder.binding.chatCard.findNavController().navigate(
                ChatFragmentDirections.actionChatFragmentToChatDetailFragment(
                    //pass the data to the detail fragment
                    item.contact.name,
                    item.contact.image,
                    position
                )
            )
        }
    }

    override fun getItemCount() :Int {
        return dataset.size
    }

    fun addChat(chat: Chat) {
        dataset.toMutableList().add(chat)
        notifyDataSetChanged()
    }
}
