package eu.javimar.groceryshopping.ui.main

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import eu.javimar.data.repository.GroceryRepository
import eu.javimar.usecases.AddItemToCart
import eu.javimar.usecases.GetGroceryList
import eu.javimar.usecases.ResetCart
import eu.javimar.usecases.SubstractItemFromCart

@Module
@InstallIn(ActivityRetainedComponent::class)
class MainDIModule {

    @Provides
    fun getGroceryListRepository(groceryRepository: GroceryRepository) =
        GetGroceryList(groceryRepository)

    @Provides
    fun addItemToCartRepository(groceryRepository: GroceryRepository) =
        AddItemToCart(groceryRepository)

    @Provides
    fun substractItemFromCartRepository(groceryRepository: GroceryRepository) =
        SubstractItemFromCart(groceryRepository)

    @Provides
    fun resetCartRepository(groceryRepository: GroceryRepository) =
        ResetCart(groceryRepository)
}