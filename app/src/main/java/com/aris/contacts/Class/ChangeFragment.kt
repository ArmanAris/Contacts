package com.aris.contacts.Class

import androidx.fragment.app.Fragment
import com.aris.contacts.R

class ChangeFragment {

    companion object {
        fun replaceFragment(fragment: Fragment, back: Boolean) {
            val fragmentManager = Base.fragmentManager
            val trans = fragmentManager!!.beginTransaction()
            if (back) {
                trans.addToBackStack(null)
            }
            trans.replace(R.id.frameLayout, fragment)
            trans.commit()
        }
    }

}