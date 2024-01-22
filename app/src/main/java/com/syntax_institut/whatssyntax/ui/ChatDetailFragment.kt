package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.adapter.ChatAdapter
import com.syntax_institut.whatssyntax.adapter.ChatDetailAdapter
import com.syntax_institut.whatssyntax.adapter.StatusAdapter
import com.syntax_institut.whatssyntax.data.Datasource
import com.syntax_institut.whatssyntax.data.model.Contact
import com.syntax_institut.whatssyntax.data.model.Message
import com.syntax_institut.whatssyntax.databinding.FragmentChatDetailBinding
import com.syntax_institut.whatssyntax.databinding.FragmentStatusDetailBinding


class ChatDetailFragment : Fragment() {
    private lateinit var binding: FragmentChatDetailBinding
    private lateinit var navConttroller: NavController
//set the variables
    private var message = 0
    private var name = ""
    private var img = 0
    private lateinit var chatDetailAdapter: ChatDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // call the arguments from nav_graph
        arguments?.let {
            // need to be the same id from nav_graph
            message = it.getInt("position")
            name = it.getString("name").toString()
            img = it.getInt("img")
        }

    }


    //create the fragment Status
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            //bring all the data from the class Datasource (the list of chats)
        val mainActivity = activity as MainActivity
        val chat = mainActivity.datasource.getChats()
        super.onViewCreated(view, savedInstanceState)
        val chats = Datasource().getChats()
        // set the message as the position of the chat
        val messages = chats[message].messages
        // set the recycle view
        val recycleView = binding.recyclerViewMessages
        chatDetailAdapter = ChatDetailAdapter(messages)
        // set the adapter
        binding.recyclerViewMessages.adapter = ChatDetailAdapter(messages)


        binding.name.text = name
        binding.img.setImageResource(img)
        // set the button to send the message
        binding.btnSendMessage.setOnClickListener {
            // get the message from the input
            val messageText = binding.inputMessage.text.toString()
            val currentChat = chat[message]
            val newMessage = Message(messageText, true)
            // add the message to the list
            chatDetailAdapter.addMessage(newMessage)
            // clear the input
            binding.inputMessage.text?.clear()
            // notify the adapter
            chatDetailAdapter = ChatDetailAdapter(messages)
            // notify the adapter
            binding.recyclerViewMessages.adapter?.notifyDataSetChanged()
            // scroll to the last message
            binding.recyclerViewMessages.layoutManager?.scrollToPosition(currentChat.messages.size - 1)
            // set the recycle view
            recycleView.setHasFixedSize(true)
        }


    }

}