package eu.javimar.groceryshopping.common

import eu.javimar.domain.Grocery

interface ShopInterface {

    fun addGrocery(grocery: Grocery)
    fun deleteGrocery(grocery: Grocery)
}