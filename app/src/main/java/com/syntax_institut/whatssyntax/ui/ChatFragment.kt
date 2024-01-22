package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.syntax_institut.whatssyntax.adapter.ChatAdapter
import com.syntax_institut.whatssyntax.data.Datasource
import com.syntax_institut.whatssyntax.databinding.FragmentChatBinding


class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding
    private lateinit var navController: NavController

    //create the fragment Chat

    /**
     * 1. Create the fragment

     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentChatBinding.inflate(inflater,container,false)
        return  binding.root
    }

    /**
     *  manipulate the layout of the fragment Chat
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        val chat = Datasource().getChats()

        val recylerView = binding.recyclerViewChat


        /**
         *  Create the adapter
         */
        recylerView.adapter = ChatAdapter(chat, navController)

        // prevent the recyclerview from changing size
        recylerView.setHasFixedSize(true)

    }
}