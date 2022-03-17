package com.mysaliev.exam1.manager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mysaliev.exam1.database.Dao
import com.mysaliev.exam1.model.CardDataItem

@Database(entities = [CardDataItem::class], version = 1)
abstract class RoomManager:RoomDatabase() {
    abstract fun cardDao(): Dao

    companion object{
        private var INSTANCE:RoomManager? = null

        fun getDatabase(context: Context):RoomManager?{
            if (INSTANCE == null){
                synchronized(RoomManager::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, RoomManager::class.java, "cards.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}