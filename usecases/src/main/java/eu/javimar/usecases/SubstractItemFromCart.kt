package eu.javimar.usecases

import eu.javimar.data.repository.GroceryRepository

class SubstractItemFromCart (private val groceryRepository: GroceryRepository) {

    suspend fun invoke(id: Int) =
        groceryRepository.subtractItem(id)
}