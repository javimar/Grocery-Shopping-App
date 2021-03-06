package eu.javimar.data.repository

import eu.javimar.data.source.LocalDataSource
import eu.javimar.data.source.RemoteDataSource
import eu.javimar.domain.Grocery

class GroceryRepository(private val remoteDataSource: RemoteDataSource,
                        private val localDataSource: LocalDataSource) {

    suspend fun getGroceryList(): List<Grocery> {

        // Only load groceries once. The rest is done in the background once a day
        if(localDataSource.isGroceryListEmpty()) {
            callAPIGroceries()
        }
        // Always return items in DB as Single Source of Truth
        return localDataSource.getAllGroceries()
    }

    private suspend fun callAPIGroceries() {
        val groceries = remoteDataSource.getGroceryList()
        localDataSource.saveGroceries(groceries)
    }

    suspend fun addItem(id: Int) = localDataSource.increaseCartQuantity(id)
    suspend fun subtractItem(id: Int) = localDataSource.decreaseCartQuantity(id)
    suspend fun resetCart() = localDataSource.resetCart()

    // Called only by the WorkManager. We suppose that after a day if the user has not checked out the cart,
    // it will get erased by uploading new products
    suspend fun reloadGroceriesInBackground()
    {
        localDataSource.deleteGroceries()
        callAPIGroceries()
    }
}