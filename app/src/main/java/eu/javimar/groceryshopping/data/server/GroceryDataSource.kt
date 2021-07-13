package eu.javimar.groceryshopping.data.server

import eu.javimar.data.source.RemoteDataSource
import eu.javimar.domain.Grocery
import eu.javimar.groceryshopping.data.toDomainGrocery

class GroceryDataSource(private val groceryService: GroceryService): RemoteDataSource {

    override suspend fun getGroceryList(): List<Grocery> =
        groceryService.retrofitGroceryService
            .getGroceryProductsAsync()
            .map {
                it.toDomainGrocery()
            }
}