package eu.javimar.groceryshopping.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Grocery::class], version = 1, exportSchema = false)
abstract class GroceryDatabase: RoomDatabase() {

    abstract fun groceryDao(): GroceryDao
}