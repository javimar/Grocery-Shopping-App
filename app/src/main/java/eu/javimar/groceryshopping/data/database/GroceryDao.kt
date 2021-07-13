package eu.javimar.groceryshopping.data.database

import androidx.room.*

@Dao
interface GroceryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertGroceries(groceries: List<Grocery>)

    @Query("SELECT COUNT(id) FROM Grocery")
    abstract fun groceryItemsCount(): Int

    @Query("SELECT * FROM Grocery")
    abstract fun getListGroceries(): List<Grocery>

    @Query("UPDATE Grocery SET quantity = :quantity WHERE id = :id")
    abstract fun updateQuantity(id: Int, quantity: Int)

//    @Query("SELECT COUNT(quantity) FROM Grocery GROUP BY type")
//    abstract fun getPriceByCategory(): ALGO
}