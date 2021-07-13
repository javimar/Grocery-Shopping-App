package eu.javimar.groceryshopping.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Grocery(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Double,
    val type: String,
    val quantity: Int
    )
