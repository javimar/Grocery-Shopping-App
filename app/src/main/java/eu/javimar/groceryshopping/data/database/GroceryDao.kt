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

}