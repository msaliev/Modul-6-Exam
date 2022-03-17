package com.mysaliev.exam1.service

import com.mysaliev.exam1.model.CardDataItem
import retrofit2.Call
import retrofit2.http.GET

interface CardService {
    @GET("cards")
    fun getAllUsers(): Call<ArrayList<CardDataItem>>
}
