package com.aris.contacts.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.aris.contacts.Class.Base
import com.aris.contacts.Class.SharedPref
import com.aris.contacts.R
import com.aris.contacts.db.AppDatabase
import com.aris.contacts.db.number.NumberPhone


class AddNumber : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_add_number, container, false)
        val type = layout.findViewById<EditText>(R.id.type_add_number)
        val number = layout.findViewById<EditText>(R.id.number_add_number)
        val btnSave = layout.findViewById<Button>(R.id.btn_save_add_number)

        btnSave.setOnClickListener {
            val idUser = SharedPref(Base.activity!!).getId()
            val typeNum = type.text.toString()
            val nemberNum = number.text.toString()
            val userNumber = NumberPhone(itemId = idUser, typePhone = typeNum, number = nemberNum)

            AppDatabase.getInstance(Base.activity!!).numberPhoneDao().insertAll(userNumber)

            Base.fragmentManager!!.beginTransaction().replace(R.id.frameLayout, ShowNumber())
                .commit()
        }

        return layout
    }


}