package eu.javimar.groceryshopping.data

import eu.javimar.domain.Grocery
import eu.javimar.groceryshopping.data.server.Grocery as ServerGrocery
import eu.javimar.groceryshopping.data.database.Grocery as DomainGrocery

fun ServerGrocery.toDomainGrocery(): Grocery =
    Grocery(
        id,
        name,
        price,
        type
    )

fun Grocery.toRoomGrocery(): DomainGrocery =
    DomainGrocery(
        id,
        name,
        price,
        type
    )

fun DomainGrocery.toDomainGrocery(): Grocery =
    Grocery(
        id,
        name,
        price,
        type
    )