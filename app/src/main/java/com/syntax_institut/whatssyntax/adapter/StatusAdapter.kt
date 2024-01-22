package com.syntax_institut.whatssyntax.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.whatssyntax.R
import com.syntax_institut.whatssyntax.data.model.Contact
import com.syntax_institut.whatssyntax.databinding.StatusListBinding
import com.syntax_institut.whatssyntax.ui.StatusFragmentDirections

class StatusAdapter(
    private var dataset: MutableList<Contact>,
    navController: NavController,
) :
    RecyclerView.Adapter<StatusAdapter.ItemViewHolder>() {

    init {
        //order the list first by status and then by name
        val sortedDataset = dataset.sortedByDescending { it.status != null }.toMutableList()
        this.dataset = sortedDataset

    }


    inner class ItemViewHolder(val binding: StatusListBinding) :
        RecyclerView.ViewHolder(binding.root)
    /**
     * hier werden neue ViewHolder erstellt
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StatusAdapter.ItemViewHolder {
        val binding = StatusListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }




    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: StatusAdapter.ItemViewHolder, position: Int) {
        //Dataset is the list of contacts


        val itemStatus = dataset[position]

        //set the name of the contact
        holder.binding.tvName.text = itemStatus.name
        holder.binding.imgProfileDetail.setImageResource(itemStatus.image)
        holder.binding.tvStatus.text = itemStatus.status?.text


        // btn to edit the status
        holder.binding.imgEdit.setOnClickListener {
            val controller = holder.binding.imgEdit.findNavController()
            controller.navigate(
                //navigate to the settings fragment
                StatusFragmentDirections.actionStatusFragmentToSettingsFragment(
                    itemStatus.name, itemStatus.number, itemStatus.image
                )
            )
        }
        // if status is not null show normally
        if (itemStatus.status != null) {
            //set the background color of the card to green
            holder.binding.statusCard.setBackgroundColor(R.color.white)

                //navigate to the status detail fragment
            holder.binding.tvStatus.setOnClickListener {
                holder.binding.tvStatus.findNavController().navigate(
                    StatusFragmentDirections.actionStatusFragmentToStatusDetailFragment(
                        itemStatus.status?.text.toString(),
                        itemStatus.name,
                        itemStatus.image
                    )
                )
            }
    // if status is null show the card with a black background and alpha 0.5
        } else if (itemStatus.status == null) {
            //set the background color of the card to white
            holder.binding.statusCard.setBackgroundColor(R.color.black)
            holder.binding.statusCard.alpha = 0.5F
            holder.binding.statusCard.isClickable = false

        }
    }
    //Länge der Liste
    override fun getItemCount(): Int {
        return dataset.size
    }


    /**
     * hier findet der Recyclingprozess statt
     * die vom ViewHolder bereitgestellten Parameter erhalten die Information des Listeneintrags
     */

    // todo: new
    fun updateContacts(newContacts: MutableList<Contact>) {
        dataset = newContacts
        notifyDataSetChanged()
    }

}
