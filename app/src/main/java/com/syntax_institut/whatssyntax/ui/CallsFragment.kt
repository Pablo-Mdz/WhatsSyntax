package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.syntax_institut.whatssyntax.adapter.CallsAdapter
import com.syntax_institut.whatssyntax.data.Datasource
import com.syntax_institut.whatssyntax.databinding.FragmentCallsBinding


class CallsFragment : Fragment() {
    private lateinit var binding: FragmentCallsBinding

    // navigation controller
    private lateinit var navController: NavController

    //create the fragment Calls


    /**
     * 1. Create the fragment

     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCallsBinding.inflate(inflater, container, false)
        return binding.root
    }


    /**
     *  manipulate the layout of the fragment Calls
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
    //bring all the data from the class Datasource (the list of calls)
        val call = Datasource().getCalls()

        val recylerView = binding.recyclerViewCalls
        recylerView.adapter = CallsAdapter(call, navController)

        recylerView.setHasFixedSize(true)

    }
}