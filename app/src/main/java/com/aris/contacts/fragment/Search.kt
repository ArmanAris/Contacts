package com.aris.contacts.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aris.contacts.Class.Base
import com.aris.contacts.R
import com.aris.contacts.adapter.SearchAdapter
import com.aris.contacts.db.AppDatabase


class Search : Fragment() {
    val content = Base.activity!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_search, container, false)

        val textSearch = layout.findViewById<EditText>(R.id.search_search)
        val recyclerView = layout.findViewById<RecyclerView>(R.id.search_rec)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(content, 3)

        textSearch.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(text: Editable?) {
                val listSearch =
                    AppDatabase.getInstance(content).userDao().searchUser(text.toString())
                recyclerView.adapter = SearchAdapter(content, listSearch)
            }

        })





        return layout
    }

}