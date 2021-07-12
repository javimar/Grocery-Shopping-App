package eu.javimar.usecases

import eu.javimar.data.repository.GroceryRepository
import eu.javimar.domain.Grocery

class GetGroceryList(private val groceryRepository: GroceryRepository) {

    suspend fun invoke(): List<Grocery> =
        groceryRepository.getGroceryList()
}