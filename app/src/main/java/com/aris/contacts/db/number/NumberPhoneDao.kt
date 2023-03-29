package com.aris.contacts.db.number

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aris.contacts.db.user.User

@Dao
interface NumberPhoneDao {
    @Insert
    fun insertAll(vararg numberPhone: NumberPhone)

    @Query("SELECT * FROM number_table")
    fun getAll(): MutableList<NumberPhone>

    @Query("SELECT * FROM number_table where item_id=:itemid")
    fun getAllById(itemid: Int): MutableList<NumberPhone>

    @Delete
    fun delete(numberPhone: NumberPhone)

    @Query("UPDATE number_table set typePhone =:type , number =:num WHERE id=:id")
    fun update(type: String, num: String, id: Int)
}