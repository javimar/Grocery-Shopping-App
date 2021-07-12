package eu.javimar.groceryshopping.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.javimar.data.source.RemoteDataSource
import eu.javimar.groceryshopping.data.server.GroceryDataSource
import eu.javimar.groceryshopping.data.server.GroceryService

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun groceryRemoteDataSourceProvider(groceryService: GroceryService): RemoteDataSource =
        GroceryDataSource(groceryService)
}