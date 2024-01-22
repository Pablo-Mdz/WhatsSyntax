package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.adapter.StatusAdapter
import com.syntax_institut.whatssyntax.data.Datasource
import com.syntax_institut.whatssyntax.data.model.Contact
import com.syntax_institut.whatssyntax.databinding.FragmentStatusDetailBinding


class StatusDetailFragment : Fragment() {
    //create the binding variable
    private lateinit var binding: FragmentStatusDetailBinding
    // create the navController variable
    private lateinit var navConttroller: NavController



    private var statusText = ""
    private var name = ""
    private var image = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            statusText = it.getString("status_text").toString()
            name = it.getString("name_detail").toString()
            image = it.getInt("img_profile_detail")
        }
    }


    //create the fragment Status
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        binding = FragmentStatusDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //get the navController
        navConttroller = findNavController()

        //get the main activity
        val mainActivity = activity as MainActivity
        //get the data from the datasource
        val status = mainActivity.datasource.getContacts()
        // create the adapter
      StatusAdapter(status, navConttroller)
        // log to test the data
        Log.d("StatusDetailFragment", "all data $status")
        //set the data to the views
        binding.name.text = name
        binding.statusText.text = statusText
        binding.imgProfileDetail.setImageResource(image)

    }

}