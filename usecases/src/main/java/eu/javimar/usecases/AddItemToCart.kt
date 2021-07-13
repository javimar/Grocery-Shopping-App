package eu.javimar.usecases

import eu.javimar.data.repository.GroceryRepository

class AddItemToCart(private val groceryRepository: GroceryRepository) {

    suspend fun invoke(id: Int) =
        groceryRepository.addItem(id)
}