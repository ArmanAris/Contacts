package com.aris.contacts.Class

import android.content.Context

class SharedPref(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("PREF", 0)
    private val editor = sharedPreferences.edit()

    private val nameKey = "firstName"
    private val lastKey = "lastName"
    private val idKey = "id"
    private val flagKey = "flag"

    fun setName(name: String) {
        editor.putString(nameKey, name)
        editor.commit()
    }

    fun setlast(last: String) {
        editor.putString(lastKey, last)
        editor.commit()
    }

    fun setId(id: Int) {
        editor.putInt(idKey, id)
        editor.commit()
    }

    fun setFlag(flag: Int) {
        editor.putInt(flagKey, flag)
        editor.commit()
    }

    fun getName(): String? {
        return sharedPreferences.getString(nameKey, "null")
    }

    fun getlast(): String? {
        return sharedPreferences.getString(lastKey, "null")
    }

    fun getId(): Int {
        return sharedPreferences.getInt(idKey, 0)
    }

    fun getFlag(): Int {
        return sharedPreferences.getInt(flagKey, 0)
    }

}