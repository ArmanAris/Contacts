package com.aris.contacts.db.number

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.aris.contacts.db.user.User

@Entity(tableName = "number_table",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["uid"],
        childColumns = ["item_id"],
        onDelete = CASCADE, onUpdate = CASCADE
    )])
data class NumberPhone(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "item_id")
    var itemId: Int?,

    @ColumnInfo(name = "typePhone")
    var typePhone: String?,

    @ColumnInfo(name = "number")
    var number: String?,
)