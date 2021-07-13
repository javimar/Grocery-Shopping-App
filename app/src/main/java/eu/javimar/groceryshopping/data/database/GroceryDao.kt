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

    @Query("UPDATE Grocery SET quantity = quantity + 1 WHERE id = :id")
    abstract fun increaseQuantity(id: Int)

    @Query("UPDATE Grocery SET quantity = quantity - 1 WHERE id = :id AND quantity > 0")
    abstract fun decreaseQuantity(id: Int)

    @Query("UPDATE Grocery SET quantity = 0 WHERE quantity <> 0")
    abstract fun resetCart()

//    @Query("SELECT COUNT(quantity) FROM Grocery GROUP BY type")
//    abstract fun getPriceByCategory(): ALGO
}