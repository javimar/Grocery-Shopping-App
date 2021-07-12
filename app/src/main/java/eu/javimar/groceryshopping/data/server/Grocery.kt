package eu.javimar.groceryshopping.data.server

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Grocery(
    val id: Int,
    val name: String,
    val price: Double,
    val type: String
): Parcelable