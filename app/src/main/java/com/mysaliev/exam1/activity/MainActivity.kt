package com.mysaliev.exam1.activity

import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mysaliev.exam1.R
import com.mysaliev.exam1.adapter.AdapterForCard
import com.mysaliev.exam1.database.CardRepasitory
import com.mysaliev.exam1.helper.Logger
import com.mysaliev.exam1.model.CardDataItem
import com.mysaliev.exam1.networking.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var cardList: ArrayList<CardDataItem>
    private lateinit var savedCardList: ArrayList<CardDataItem>
    private lateinit var adapter: AdapterForCard
    private lateinit var recyclerView: RecyclerView
    lateinit var add_card: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvCards)
        add_card = findViewById(R.id.ivAddCard)


        add_card.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        initViews()


    }

    private fun initViews() {




        cardList = ArrayList()
        savedCardList = ArrayList()

        if (isInternetAvailable()) {
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerView.layoutManager = layoutManager
            refreshAdapter(cardList)
            getCards()
        } else {
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerView.layoutManager = layoutManager
            refreshAdapter(savedCardList)
            getCardsFromDatabse()

        }


    }

    private fun refreshAdapter(list: ArrayList<CardDataItem>) {
        adapter = AdapterForCard(this, list)
        recyclerView.adapter = adapter

    }

    private fun getCardsFromDatabse() {
        val repository = CardRepasitory(application)
        savedCardList.addAll(repository.getUsers())
        adapter.notifyDataSetChanged()

    }

    private fun getCards() {
        RetrofitHttp.cardService.getAllUsers().enqueue(object : Callback<ArrayList<CardDataItem>> {
            override fun onResponse(
                call: Call<ArrayList<CardDataItem>>,
                response: Response<ArrayList<CardDataItem>>
            ) {
                Logger.d("responce", response.body().toString())
                cardList.clear()
                cardList.addAll(response.body()!!)
                adapter.notifyDataSetChanged()

                saveToDatabase(response.body()!!)
            }

            override fun onFailure(call: Call<ArrayList<CardDataItem>>, t: Throwable) {
                Logger.e("error", t.localizedMessage)
            }

        })
    }

    private fun saveToDatabase(respond: ArrayList<CardDataItem>) {
        val repository = CardRepasitory(application)
        repository.deleteUsers()
        for (i in respond) {
            val cardDataItem = CardDataItem(i.id!!, i.full_name, i.card_number, i.date, "")

            repository.saveUser(cardDataItem)

        }
    }


    private fun isInternetAvailable(): Boolean {
        val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val infoMobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        val infoWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return infoMobile!!.isConnected || infoWifi!!.isConnected
    }
}