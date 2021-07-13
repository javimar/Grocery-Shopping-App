package eu.javimar.groceryshopping.ui.cart

import com.xwray.groupie.databinding.BindableItem
import eu.javimar.domain.Grocery
import eu.javimar.groceryshopping.R
import eu.javimar.groceryshopping.databinding.CartBodyItemBinding

class CartBody(private val grocery: Grocery): BindableItem<CartBodyItemBinding>() {

    override fun bind(viewBinding: CartBodyItemBinding, position: Int) {

        viewBinding.grocery = grocery
    }
    override fun getLayout() = R.layout.cart_body_item

}