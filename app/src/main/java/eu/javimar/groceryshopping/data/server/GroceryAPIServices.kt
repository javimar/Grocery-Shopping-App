package eu.javimar.groceryshopping.data.server

import retrofit2.http.GET

interface GroceryAPIServices {

    @GET("groceryProducts.json")
    suspend fun getGroceryProductsAsync():
            List<Grocery>

}