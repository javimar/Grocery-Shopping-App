package eu.javimar.data.repository

import eu.javimar.data.source.RemoteDataSource
import eu.javimar.domain.Grocery

class GroceryRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getGroceryList(): List<Grocery> {
        return remoteDataSource.getGroceryList()
    }
}