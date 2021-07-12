package eu.javimar.groceryshopping.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.javimar.data.repository.GroceryRepository
import eu.javimar.data.source.RemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun groceryRepositoryProvider(remoteDataSource: RemoteDataSource) =
        GroceryRepository(remoteDataSource)
}