package com.aris.contacts.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aris.contacts.Class.Base
import com.aris.contacts.R
import com.aris.contacts.adapter.SearchAdapter
import com.aris.contacts.db.AppDatabase

class ShowFavorite : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val layout= inflater.inflate(R.layout.fragment_show_favorite, container, false)

        val recyclerView = layout.findViewById<RecyclerView>(R.id.recycler_favorite)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(Base.activity!!, 3)
        val listSearch =
            AppDatabase.getInstance(Base.activity!!).userDao().getFavorite()
        recyclerView.adapter = SearchAdapter(Base.activity!!, listSearch)

        return layout
    }


}