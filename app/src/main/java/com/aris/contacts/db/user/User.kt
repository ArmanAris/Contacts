package com.aris.contacts.db.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    val uid: Int = 0,

    @ColumnInfo(name = "first_name")
    var firstName: String?,

    @ColumnInfo(name = "last_name")
    var lastName: String?,

    @ColumnInfo(name = "Favorite_Flag")
    var Flag: Int = 0,
)
