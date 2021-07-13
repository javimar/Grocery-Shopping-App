package eu.javimar.groceryshopping.ui.cart

import com.xwray.groupie.databinding.BindableItem
import eu.javimar.groceryshopping.R
import eu.javimar.groceryshopping.databinding.CartHeaderItemBinding

class CartHeader (private val grocery: String,
                  private val price: Double): BindableItem<CartHeaderItemBinding>() {

    override fun bind(viewBinding: CartHeaderItemBinding, position: Int) {


    }
    override fun getLayout() = R.layout.cart_header_item
}