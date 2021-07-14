package eu.javimar.usecases

import eu.javimar.data.repository.GroceryRepository

class ReloadGroceriesBackground(private val groceryRepository: GroceryRepository) {

    suspend fun invoke() = groceryRepository.reloadGroceriesInBackground()
}