package eu.javimar.groceryshopping.ui.main

import com.xwray.groupie.databinding.BindableItem
import eu.javimar.domain.Grocery
import eu.javimar.groceryshopping.R
import eu.javimar.groceryshopping.common.ShopInterface
import eu.javimar.groceryshopping.databinding.GroceryBodyItemBinding

class GroceryBody(private val grocery: Grocery): BindableItem<GroceryBodyItemBinding>(), ShopInterface {

    override fun bind(viewBinding: GroceryBodyItemBinding, position: Int) {

        viewBinding.grocery = grocery

        viewBinding.addItemButton.setOnClickListener {
            addGrocery(grocery)
        }
        viewBinding.deleteItemButton.setOnClickListener {
            deleteGrocery(grocery)
        }

    }
    override fun getLayout() = R.layout.grocery_body_item

    override fun addGrocery(grocery: Grocery) {

    }

    override fun deleteGrocery(grocery: Grocery) {

    }
}