package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentViewHolder
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.adapter.StatusAdapter
import com.syntax_institut.whatssyntax.data.Datasource
import com.syntax_institut.whatssyntax.databinding.FragmentCallsBinding
import com.syntax_institut.whatssyntax.databinding.FragmentSettingsBinding
import com.syntax_institut.whatssyntax.databinding.FragmentStatusBinding
import com.syntax_institut.whatssyntax.databinding.FragmentStatusDetailBinding


class StatusFragment : Fragment() {
    private lateinit var binding: FragmentStatusBinding

    private lateinit var navConttroller: NavController

    private lateinit var statusAdapter: StatusAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            val nameId = it.getString("nameId").toString()
            val numberId = it.getString("numberId").toString()

        }
    }


    //create the fragment Status
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentStatusBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navConttroller = findNavController()


        val mainActivity = activity as MainActivity
        val status = mainActivity.datasource.getContacts()

        //call the recyclerView
        val recylerView = binding.recyclerViewStatus
        // set the adapter
        statusAdapter = StatusAdapter(status, navConttroller)
        recylerView.adapter = statusAdapter


    }

}