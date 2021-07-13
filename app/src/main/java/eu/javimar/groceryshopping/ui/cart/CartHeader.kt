package eu.javimar.groceryshopping.ui.cart

import com.xwray.groupie.databinding.BindableItem
import eu.javimar.groceryshopping.R
import eu.javimar.groceryshopping.databinding.CartHeaderItemBinding

class CartHeader (private val groceryType: String,
                  private val price: Double): BindableItem<CartHeaderItemBinding>() {

    override fun bind(viewBinding: CartHeaderItemBinding, position: Int) {

        viewBinding.price = price
        viewBinding.groceryType = groceryType
    }
    override fun getLayout() = R.layout.cart_header_item
}