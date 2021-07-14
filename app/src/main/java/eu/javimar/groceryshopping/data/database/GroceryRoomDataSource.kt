package eu.javimar.groceryshopping.data.database

import eu.javimar.data.source.LocalDataSource
import eu.javimar.domain.Grocery
import eu.javimar.groceryshopping.data.toDomainGrocery
import eu.javimar.groceryshopping.data.toRoomGrocery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GroceryRoomDataSource(db: GroceryDatabase): LocalDataSource {

    private val groceryDao = db.groceryDao()

    override suspend fun isGroceryListEmpty(): Boolean =
        withContext(Dispatchers.IO) { groceryDao.groceryItemsCount() <= 0 }

    override suspend fun getAllGroceries(): List<Grocery> = withContext(Dispatchers.IO) {
        groceryDao.getListGroceries().map { it.toDomainGrocery() }
    }

    override suspend fun saveGroceries(grocery: List<Grocery>) {
        withContext(Dispatchers.IO) {
            groceryDao.insertGroceries(grocery.map { it.toRoomGrocery() }) }
    }

    override suspend fun resetCart() =
        withContext(Dispatchers.IO) { groceryDao.resetCart() }

    override suspend fun decreaseCartQuantity(id: Int) =
        withContext(Dispatchers.IO) { groceryDao.decreaseQuantity(id) }

    override suspend fun increaseCartQuantity(id: Int) =
        withContext(Dispatchers.IO) { groceryDao.increaseQuantity(id) }

    override suspend fun deleteGroceries() =
        withContext(Dispatchers.IO) { groceryDao.deleteAllGroceries() }
}

