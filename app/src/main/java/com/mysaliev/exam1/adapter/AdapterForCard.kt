package com.mysaliev.exam1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mysaliev.exam1.R
import com.mysaliev.exam1.model.CardDataItem

class AdapterForCard (val context: Context, val list:ArrayList<CardDataItem>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class UserViewHolder(view: View): RecyclerView.ViewHolder(view){
        val cardName = view.findViewById<TextView>(R.id.tvCardHolder)
        val cardNumber = view.findViewById<TextView>(R.id.tvCardNumber)
        val tv_data = view.findViewById<TextView>(R.id.tvExpireDate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cards_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val card = list[position]
        if (holder is UserViewHolder){
            holder.cardName.text = card.full_name
            holder.cardNumber.text = card.card_number
            holder.tv_data.text = card.date
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}