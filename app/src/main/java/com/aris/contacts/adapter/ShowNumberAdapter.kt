package com.aris.contacts.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aris.contacts.R
import com.aris.contacts.db.AppDatabase
import com.aris.contacts.db.number.NumberPhone

class ShowNumberAdapter(private val context: Context, private val list: MutableList<NumberPhone>) :
    RecyclerView.Adapter<ShowNumberAdapter.holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val view = LayoutInflater.from(context).inflate(R.layout.show_number_adapter, parent, false)
        return holder(view, list)
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        holder.txtType.text = list[position].typePhone.toString()
        holder.txtNumber.text = list[position].number.toString()

        holder.call.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel: ${list[position].number}")
            context.startActivity(intent)
        }

        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class holder(itemView: View, val numlist: MutableList<NumberPhone>) :
        RecyclerView.ViewHolder(itemView) {
        val txtType = itemView.findViewById<TextView>(R.id.show_type)
        val txtNumber = itemView.findViewById<TextView>(R.id.show_number)
        val delete = itemView.findViewById<ImageButton>(R.id.delete_number)
        val edite = itemView.findViewById<ImageButton>(R.id.edit_number)
        val call = itemView.findViewById<Button>(R.id.call)

        fun bind(number: NumberPhone) {
            delete.setOnClickListener {
                AppDatabase.getInstance(context).numberPhoneDao().delete(number)
                numlist.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }

            edite.setOnClickListener {
                val view = LayoutInflater.from(context).inflate(R.layout.dialog_edit, null)
                val type = view.findViewById<EditText>(R.id.type_add_number)
                val numberPhone = view.findViewById<EditText>(R.id.number_add_number)
                val save = view.findViewById<Button>(R.id.btn_save_add_number)

                type.setText(number.typePhone)
                numberPhone.setText(number.number)

                val alert = AlertDialog.Builder(context).setView(view).create()
                alert.show()

                save.setOnClickListener {
                    number.typePhone = type.text.toString()
                    number.number = numberPhone.text.toString()

                    AppDatabase.getInstance(context).numberPhoneDao()
                        .update(number.typePhone.toString(), number.number.toString(), number.id)

                    notifyItemChanged(adapterPosition, number)

                    alert.dismiss()
                }
            }

        }
    }
}