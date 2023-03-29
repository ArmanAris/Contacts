package com.aris.contacts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aris.contacts.Class.Base
import com.aris.contacts.R
import com.aris.contacts.db.user.User

class FavoriteAdapter(private val context: Context, private val listUser: MutableList<User>) :
    RecyclerView.Adapter<FavoriteAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.show_user_search_adapter, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.nameAdapterSearch.text = listUser[position].firstName.toString()
        holder.lastAdapterSearch.text = listUser[position].lastName.toString()
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameAdapterSearch = itemView.findViewById<TextView>(R.id.first_name_adapter_search)
        val lastAdapterSearch = itemView.findViewById<TextView>(R.id.last_name_adapter_search)
    }

}