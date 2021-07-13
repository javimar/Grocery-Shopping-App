package eu.javimar.groceryshopping.ui.main

import com.xwray.groupie.databinding.BindableItem
import eu.javimar.domain.Grocery
import eu.javimar.groceryshopping.R
import eu.javimar.groceryshopping.common.ShopInterface
import eu.javimar.groceryshopping.databinding.GroceryBodyItemBinding

class GroceryBody(private val grocery: Grocery,
                  private val shopInterface: ShopInterface): BindableItem<GroceryBodyItemBinding>() {

    override fun bind(viewBinding: GroceryBodyItemBinding, position: Int) {

        viewBinding.grocery = grocery
        viewBinding.shop = shopInterface
    }
    override fun getLayout() = R.layout.grocery_body_item

}