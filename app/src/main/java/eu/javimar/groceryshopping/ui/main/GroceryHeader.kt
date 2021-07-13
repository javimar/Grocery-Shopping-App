package eu.javimar.groceryshopping.ui.main

import com.xwray.groupie.databinding.BindableItem
import eu.javimar.groceryshopping.R
import eu.javimar.groceryshopping.databinding.GroceryHeaderItemBinding

class GroceryHeader(private val groceryType: String,
                    private val price: Double): BindableItem<GroceryHeaderItemBinding>() {

    override fun bind(viewBinding: GroceryHeaderItemBinding, position: Int) {

        viewBinding.price = price
        viewBinding.groceryType = groceryType
    }
    override fun getLayout() = R.layout.grocery_header_item
}