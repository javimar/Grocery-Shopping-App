package eu.javimar.data.source

import eu.javimar.domain.Grocery

interface LocalDataSource {

    suspend fun isGroceryListEmpty(): Boolean
    suspend fun getAllGroceries(): List<Grocery>
    suspend fun saveGroceries(grocery: List<Grocery>)
    suspend fun resetCart(id: Int)
    suspend fun updateCartQuantity(id: Int, quantity: Int)
}