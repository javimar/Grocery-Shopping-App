package eu.javimar.groceryshopping.ui.main

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import eu.javimar.data.repository.GroceryRepository
import eu.javimar.usecases.GetGroceryList

@Module
@InstallIn(ActivityRetainedComponent::class)
class MainDIModule {

    @Provides
    fun getGroceryListRepository(groceryRepository: GroceryRepository) =
        GetGroceryList(groceryRepository)
}