package eu.javimar.data.source

import eu.javimar.domain.Grocery

interface RemoteDataSource {

    suspend fun getGroceryList(): List<Grocery>
}