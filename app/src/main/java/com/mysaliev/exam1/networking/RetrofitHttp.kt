package com.mysaliev.exam1.networking

import com.mysaliev.exam1.service.CardService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHttp {
    companion object{
        val TAG:String = RetrofitHttp::class.java.simpleName
        const val IS_TESTER:Boolean = true

        const val SERVER_DEVELOPMENT = "https://6232b3ca8364d63035c1fb37.mockapi.io/"
        const val SERVER_PRODUCTION = "https://6232b3ca8364d63035c1fb37.mockapi.io/cards/api/v1/"

        private fun server():String{
            return  if (IS_TESTER){
                SERVER_DEVELOPMENT
            }else{
                SERVER_PRODUCTION
            }
        }

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(server())
                .build()
        }

        val cardService: CardService = getRetrofit().create(CardService::class.java)

    }
}