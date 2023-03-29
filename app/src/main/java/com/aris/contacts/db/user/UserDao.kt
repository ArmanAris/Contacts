package com.aris.contacts.db.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insertAll(vararg users: User)

    @Query("SELECT * FROM user_table ORDER BY first_name ASC")
    fun getAll(): MutableList<User>

    @Query("SELECT * FROM user_table")
    fun getAllLive(): LiveData<List<User>>

    @Delete
    fun delete(user: User)

    @Query("UPDATE user_table set first_name =:name , last_name =:last WHERE uid=:id")
    fun update(name: String, last: String, id: Int)

    @Query("SELECT * FROM user_table WHERE first_name LIKE '%' || :name || '%' or last_name LIKE '%' || :name || '%'")
    fun searchUser(name: String): MutableList<User>

    @Query("UPDATE user_table set Favorite_Flag =:flag WHERE uid=:id")
    fun updateFlag(flag: Int, id: Int)

    @Query("SELECT Favorite_Flag FROM user_table WHERE uid=:id Limit 1")
    fun getFlag(id: Int): Int

    @Query("SELECT * FROM user_table WHERE Favorite_Flag=1 ORDER BY first_name ASC")
    fun getFavorite(): MutableList<User>
}