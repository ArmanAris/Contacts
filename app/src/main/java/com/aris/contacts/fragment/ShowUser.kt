package com.aris.contacts.fragment

import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aris.contacts.Class.Base
import com.aris.contacts.Class.ChangeFragment
import com.aris.contacts.R
import com.aris.contacts.adapter.UserAdapter
import com.aris.contacts.db.AppDatabase
import com.aris.contacts.db.user.User
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ShowUser : Fragment() {

    lateinit var layout: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        layout = inflater.inflate(R.layout.fragment_show_user, container, false)
        cost()

        layout.findViewById<Button>(R.id.search).setOnClickListener {
            ChangeFragment.replaceFragment(Search(), true)
        }

        layout.findViewById<Button>(R.id.favorite).setOnClickListener {
            ChangeFragment.replaceFragment(ShowFavorite(), true)
        }

        return layout
    }


    lateinit var btnAddUser: FloatingActionButton
    lateinit var recyclerView: RecyclerView
    private fun views() {
        btnAddUser = layout.findViewById(R.id.addUser)

        val listUser = AppDatabase.getInstance(Base.activity!!).userDao().getAll()


        recyclerView = layout.findViewById(R.id.recyclerShowUser)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(Base.activity!!)
        val adapter = UserAdapter(Base.activity!!, listUser)
        recyclerView.adapter = adapter
    }

    private fun cost() {
        views()
        btnAddUser.setOnClickListener {
            ChangeFragment.replaceFragment(AddUser(), true)
        }
    }



}