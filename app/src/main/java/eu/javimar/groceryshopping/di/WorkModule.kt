package eu.javimar.groceryshopping.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.javimar.data.repository.GroceryRepository
import eu.javimar.usecases.ReloadGroceriesBackground
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class WorkModule {
    @Singleton
    @Provides
    fun provideWorkerDependency(groceryRepository: GroceryRepository) =
        ReloadGroceriesBackground(groceryRepository)

}