package com.aris.contacts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aris.contacts.db.number.NumberPhone
import com.aris.contacts.db.number.NumberPhoneDao
import com.aris.contacts.db.user.User
import com.aris.contacts.db.user.UserDao

@Database(entities = [User::class, NumberPhone::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun numberPhoneDao(): NumberPhoneDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance =
                    INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_dataBase"
                    ).allowMainThreadQueries().build()
                }

                return instance
            }
        }
    }
}
