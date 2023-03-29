package com.aris.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aris.contacts.Class.Base
import com.aris.contacts.fragment.AddUser
import com.aris.contacts.fragment.ShowUser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Base.activity = this
        Base.fragmentManager = supportFragmentManager

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, ShowUser()).commit()
    }
}

