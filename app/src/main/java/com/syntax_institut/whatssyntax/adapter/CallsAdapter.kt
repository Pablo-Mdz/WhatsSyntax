package com.syntax_institut.whatssyntax.adapter

import android.content.Intent
import android.net.Uri
import android.provider.CallLog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.whatssyntax.R
import com.syntax_institut.whatssyntax.data.model.Call
import com.syntax_institut.whatssyntax.databinding.CallListBinding

class CallsAdapter(
    private val dataset: List<Call>,
    navController: NavController,
) : RecyclerView.Adapter<CallsAdapter.ItemViewholder>() {

    inner class ItemViewholder(val binding: CallListBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallsAdapter.ItemViewholder {
        val binding = CallListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewholder(binding)
    }
    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: CallsAdapter.ItemViewholder, position: Int) {
        val itemCalls = dataset[position]

        // to show the image of the contact
        holder.binding.imgProfileDetail.setImageResource(itemCalls.contact.image)
        holder.binding.tvName.text = itemCalls.contact.name
        holder.binding.tvCalls.text = itemCalls.time

        // to show the type of the call
        // direction of the arrow depending of the incoming and accepted booleans status
        if (!itemCalls.incoming && !itemCalls.accepted) {
            holder.binding.imgCall.setImageResource(R.drawable.icon_call_missed)
        } else if (itemCalls.incoming && !itemCalls.accepted) {
            holder.binding.imgCall.rotation = 180F
            holder.binding.imgCall.setImageResource(R.drawable.icon_call_missed)
        } else if (!itemCalls.incoming && itemCalls.accepted) {
            holder.binding.imgCall.setImageResource(R.drawable.icon_call_accepted)
        } else if (itemCalls.incoming && itemCalls.accepted){
            holder.binding.imgCall.rotation = 180F
            holder.binding.imgCall.setImageResource(R.drawable.icon_call_accepted)
        }


        // to call the number of the contact
        holder.binding.callCard.setOnClickListener {
            // to call the number of the contact
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.putExtra("name", itemCalls.contact.number)
            dialIntent.data = Uri.parse("tel:${itemCalls.contact.number}")
            holder.itemView.context.startActivity(dialIntent)
        }
    }

}
