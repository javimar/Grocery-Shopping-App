package eu.javimar.usecases

import eu.javimar.data.repository.GroceryRepository

class ResetCart(private val groceryRepository: GroceryRepository) {

    suspend fun invoke() =
        groceryRepository.resetCart()
}