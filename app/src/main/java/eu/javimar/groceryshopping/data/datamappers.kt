package eu.javimar.groceryshopping.data

import eu.javimar.domain.Grocery
import eu.javimar.groceryshopping.data.server.Grocery as ServerGroceryResults

fun ServerGroceryResults.toDomainGroceryResults(): Grocery =
    Grocery(
        id,
        name,
        price,
        type
    )