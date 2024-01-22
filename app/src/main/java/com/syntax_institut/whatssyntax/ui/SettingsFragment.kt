package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.R
import com.syntax_institut.whatssyntax.adapter.ChatAdapter
import com.syntax_institut.whatssyntax.data.Datasource
import com.syntax_institut.whatssyntax.data.model.Contact
import com.syntax_institut.whatssyntax.data.model.Profile
import com.syntax_institut.whatssyntax.data.model.Status
import com.syntax_institut.whatssyntax.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var navController: NavController


    private val datasource = Datasource()
    private var name: String? = null
    private var number: String? = null
    private var img = 0
    private var originalContact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString("name", "") ?: ""
            number = it.getString("number", "") ?: ""
            val mainActivity = activity as MainActivity
            originalContact = mainActivity.datasource.getContacts()
                .find { it.name == name && it.number == number }
            img = it.getInt("img", R.drawable.img_default)
        }
    }

    // Hier wird das Fragment aufgebaut
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get all the contacts updated
        val mainActivity = activity as MainActivity
        val contacts = mainActivity.datasource.getContacts()


        navController = findNavController()

        binding.textInputName.editText?.setText(name)
        binding.textInputTelephone.editText?.setText(number)
        binding.imgProfile.setImageResource(img)

        //update btn
        binding.btnUpdate.setOnClickListener {

            // set the input with the name and number from the contact
            val name = binding.textInputName.editText?.text.toString()
            val number = binding.textInputTelephone.editText?.text.toString()


            if (originalContact != null) {
                // Update the contact in Datasource
                val updatedContact = Contact(
                    name,
                    number,
                    // if the image is not updated, keep the original image
                    originalContact?.image ?: R.drawable.img_default,
                    // if the status is not updated, keep the original status
                    originalContact?.status ?: Status("Heute ist das wetter super")
                )
                // update the contact in the list
                mainActivity.datasource.updateContact(originalContact!!, updatedContact)

                // Update the proflie in  Datasource
                datasource.setProfile(Profile(name, number, R.drawable.img_default))

                // Toast message
                Toast.makeText(requireContext(), "Kontakt wurde Update", Toast.LENGTH_SHORT).show()

                // clean the feilds after update
                binding.textInputName.editText?.setText("")
                binding.textInputTelephone.editText?.setText("")
            } else {
                Toast.makeText(
                    requireContext(),
                    "Kontakt nicht gefunden",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        //save btn
        binding.btnSave.setOnClickListener {
            // set the input with the name and number from the contact
            val name = binding.textInputName.editText?.text.toString()
            val number = binding.textInputTelephone.editText?.text.toString()
            // add the new contact to the list
            if (name.isNotEmpty() && number.isNotEmpty()) {
                val newContact = Contact(
                    name,
                    number,
                    image = R.drawable.img_default,
                    Status("$name use WhatSyntax!!")
                )
                // add contact to the list
                contacts.add(newContact)

                Toast.makeText(
                    // requireContext() is the same as context
                    requireContext(),
                    "Kontakt wurde erfolgreich hinzugefügt",
                    Toast.LENGTH_SHORT
                ).show()

                // clean the feilds after save
                binding.inName.setText("")
                binding.inPhone.setText("")
            } else {
                Toast.makeText(
                    requireContext(),
                    "Bitte fülle beide Felder aus",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

    }
}




