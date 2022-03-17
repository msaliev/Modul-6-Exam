package com.mysaliev.exam1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
data class  CardDataItem(
    @PrimaryKey
    val id: String,
    val card_number: String,
    val cvv: String,
    val date: String,
    val full_name: String

)