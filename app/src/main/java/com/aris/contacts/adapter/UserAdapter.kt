package com.aris.contacts.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aris.contacts.Class.Base
import com.aris.contacts.Class.SharedPref
import com.aris.contacts.R
import com.aris.contacts.db.AppDatabase
import com.aris.contacts.db.user.User
import com.aris.contacts.fragment.ShowNumber

class UserAdapter(private val context: Context, private val userList: MutableList<User>) :
    RecyclerView.Adapter<UserAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_adapter, parent, false)
        return Holder(view, context, userList)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = userList[position].firstName.toString()
        holder.last.text = userList[position].lastName.toString()

        holder.bind(userList[position])

        holder.itemView.setOnClickListener {

            SharedPref(context).setName(userList[position].firstName.toString())
            SharedPref(context).setlast(userList[position].lastName.toString())
            SharedPref(context).setId(userList[position].uid)
            SharedPref(context).setFlag(userList[position].Flag)

            Base.fragmentManager!!.beginTransaction().replace(R.id.frameLayout, ShowNumber())
                .addToBackStack(null).commit()
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class Holder(itemView: View, val context: Context, val mlist: MutableList<User>) :
        RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.first_name_adapter_search)
        val last = itemView.findViewById<TextView>(R.id.last_name_adapter_search)
        val btnDelete =
            itemView.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(
                R.id.ActionButton_Delete)
        val btnEdit =
            itemView.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(
                R.id.floatingAction_edit)


        @SuppressLint("InflateParams")
        fun bind(user: User) {
            //-----------Delete----------------
            btnDelete.setOnClickListener {
                AppDatabase.getInstance(context).userDao().delete(user)
                mlist.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }

            //---------Edite------------------
            btnEdit.setOnClickListener {
                val view = LayoutInflater.from(context).inflate(R.layout.dialog_edit, null)
                val name = view.findViewById<EditText>(R.id.type_add_number)
                val last = view.findViewById<EditText>(R.id.number_add_number)
                val save = view.findViewById<Button>(R.id.btn_save_add_number)


                name.setText(user.firstName)
                last.setText(user.lastName)

                val alert = AlertDialog.Builder(context).setView(view).create()
                alert.show()

                save.setOnClickListener {
                    user.firstName = name.text.toString()
                    user.lastName = last.text.toString()

                    AppDatabase.getInstance(context).userDao()
                        .update(user.firstName.toString(), user.lastName.toString(), user.uid)

                    notifyItemChanged(adapterPosition, user)

                    alert.dismiss()
                }
            }


        }
    }

}