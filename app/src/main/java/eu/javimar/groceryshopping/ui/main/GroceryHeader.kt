package eu.javimar.groceryshopping.ui.main

import com.xwray.groupie.databinding.BindableItem
import eu.javimar.domain.Grocery
import eu.javimar.groceryshopping.R
import eu.javimar.groceryshopping.databinding.GroceryHeaderItemBinding

class GroceryHeader(private val grocery: String,
                    private val price: Double): BindableItem<GroceryHeaderItemBinding>() {

    override fun bind(viewBinding: GroceryHeaderItemBinding, position: Int) {

        viewBinding.grocery = grocery
        viewBinding.totalCatPrice = price
    }
    override fun getLayout() = R.layout.grocery_header_item
}