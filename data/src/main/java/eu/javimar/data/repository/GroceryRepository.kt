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
}