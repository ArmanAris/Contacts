package com.aris.contacts.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aris.contacts.Class.Base
import com.aris.contacts.Class.SharedPref
import com.aris.contacts.R
import com.aris.contacts.adapter.ShowNumberAdapter
import com.aris.contacts.db.AppDatabase


class ShowNumber : Fragment() {
    lateinit var layout: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        layout = inflater.inflate(R.layout.fragment_show_number, container, false)
        val titleNameFrame = layout.findViewById<TextView>(R.id.name_number)
        val titleNameToolbar = layout.findViewById<TextView>(R.id.txt_name_detail_toolbar)
        val btnAddNumber = layout.findViewById<Button>(R.id.btn_go_add_phone)
        val recyclerView = layout.findViewById<RecyclerView>(R.id.rec_detail_user)


        val text =
            "${SharedPref(Base.activity!!).getName()} ${SharedPref(Base.activity!!).getlast()}"
        titleNameFrame.setText(text)
        titleNameToolbar.setText(text)

        btnAddNumber.setOnClickListener {
            Base.fragmentManager!!.beginTransaction().replace(R.id.frameLayout, AddNumber())
                .addToBackStack(null).commit()
        }

        val listNumber = AppDatabase.getInstance(Base.activity!!).numberPhoneDao()
            .getAllById(SharedPref(Base.activity!!).getId())
        val adapter = ShowNumberAdapter(Base.activity!!, listNumber)
        recyclerView.layoutManager = LinearLayoutManager(Base.activity!!)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        favorite()

        return layout
    }

    private fun check() {


        layout.findViewById<ImageView>(R.id.btn_fav_detail).setOnClickListener {
            if (SharedPref(Base.activity!!).getFlag() == 1) {
                SharedPref(Base.activity!!).setFlag(0)

                AppDatabase.getInstance(Base.activity!!).userDao()
                    .updateFlag(0, SharedPref(Base.activity!!).getId())

                layout.findViewById<ImageView>(R.id.btn_fav_detail)
                    .setImageResource(R.drawable.ic_baseline_favorite_border_24)
            } else {
                AppDatabase.getInstance(Base.activity!!).userDao()
                    .updateFlag(1, SharedPref(Base.activity!!).getId())
                layout.findViewById<ImageView>(R.id.btn_fav_detail)
                    .setImageResource(R.drawable.ic_baseline_favorite_24)
            }
        }
    }

    private fun favorite() {

        if (AppDatabase.getInstance(Base.activity!!).userDao()
                .getFlag(SharedPref(Base.activity!!).getId()) == 1
        ) {
            layout.findViewById<ImageView>(R.id.btn_fav_detail)
                .setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            layout.findViewById<ImageView>(R.id.btn_fav_detail)
                .setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }

        layout.findViewById<ImageView>(R.id.btn_fav_detail).setOnClickListener {
            if (AppDatabase.getInstance(Base.activity!!).userDao()
                    .getFlag(SharedPref(Base.activity!!).getId()) == 1

            ){
                AppDatabase.getInstance(Base.activity!!).userDao().updateFlag(0,SharedPref(Base.activity!!).getId())
                if (AppDatabase.getInstance(Base.activity!!).userDao()
                        .getFlag(SharedPref(Base.activity!!).getId()) == 1
                ) {
                    layout.findViewById<ImageView>(R.id.btn_fav_detail)
                        .setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    layout.findViewById<ImageView>(R.id.btn_fav_detail)
                        .setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }else{
                AppDatabase.getInstance(Base.activity!!).userDao().updateFlag(1,SharedPref(Base.activity!!).getId())
                if (AppDatabase.getInstance(Base.activity!!).userDao()
                        .getFlag(SharedPref(Base.activity!!).getId()) == 1
                ) {
                    layout.findViewById<ImageView>(R.id.btn_fav_detail)
                        .setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    layout.findViewById<ImageView>(R.id.btn_fav_detail)
                        .setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }
        }
    }


}