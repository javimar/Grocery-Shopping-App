package eu.javimar.domain

data class Cart(
    val id: Int,
    val name: String,
    val price: Double,
    val type: String,
    val quantity: Int,
    val totalPrice: Double
)
