package com.aris.contacts.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.aris.contacts.Class.Base
import com.aris.contacts.R
import com.aris.contacts.db.AppDatabase
import com.aris.contacts.db.user.User


class AddUser : Fragment() {
    lateinit var layout: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        layout = inflater.inflate(R.layout.fragment_add_user, container, false)
        cost()
        return layout
    }


    private fun cost() {
        layout.findViewById<Button>(R.id.btn_save_add_number).setOnClickListener {
            val name = layout.findViewById<EditText>(R.id.type_add_number).text.toString()
            val last = layout.findViewById<EditText>(R.id.number_add_number).text.toString()

            val user = User(firstName = name, lastName = last)

            AppDatabase.getInstance(Base.activity!!).userDao().insertAll(user)

            Base.fragmentManager!!.beginTransaction().replace(R.id.frameLayout, ShowUser()).commit()

            val users = AppDatabase.getInstance(Base.activity!!).userDao().getAll()
            users.forEach {
                Log.d("7171", it.toString())
            }
        }
    }


}