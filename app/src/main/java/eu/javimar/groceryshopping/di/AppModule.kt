package eu.javimar.groceryshopping.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.javimar.data.source.LocalDataSource
import eu.javimar.data.source.RemoteDataSource
import eu.javimar.groceryshopping.data.database.GroceryDatabase
import eu.javimar.groceryshopping.data.database.GroceryRoomDataSource
import eu.javimar.groceryshopping.data.server.GroceryDataSource
import eu.javimar.groceryshopping.data.server.GroceryService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(@ApplicationContext appContext: Context): GroceryDatabase = Room.databaseBuilder(
        appContext,
        GroceryDatabase::class.java,
        "groceries-db"
    ).build()

    @Provides
    fun groceryRemoteDataSourceProvider(groceryService: GroceryService): RemoteDataSource =
        GroceryDataSource(groceryService)

    @Provides
    fun localDataSourceProvider(db: GroceryDatabase): LocalDataSource = GroceryRoomDataSource(db)
}