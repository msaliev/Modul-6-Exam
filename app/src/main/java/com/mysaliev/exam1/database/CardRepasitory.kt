package com.mysaliev.exam1.database

import android.app.Application
import com.mysaliev.exam1.helper.Logger
import com.mysaliev.exam1.manager.RoomManager
import com.mysaliev.exam1.model.CardDataItem

class CardRepasitory(application: Application) {
    val TAG:String = CardRepasitory::class.java.simpleName

    private val db = RoomManager.getDatabase(application)
    private val cardDao:Dao = db!!.cardDao()

    fun getUsers():List<CardDataItem>{
        Logger.d(TAG, "Done")

        return cardDao.getUsers()
    }

    fun saveUser(cardDataItem: CardDataItem){
        Logger.d(TAG, "Saved")
        cardDao.saveUser(cardDataItem)
    }

    fun deleteUsers(){
        Logger.d(TAG, "Database cleared")

        cardDao.deleteAllUsers()
    }
}