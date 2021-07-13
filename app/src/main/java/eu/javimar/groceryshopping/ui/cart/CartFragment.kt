package eu.javimar.groceryshopping.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.javimar.groceryshopping.R
import eu.javimar.groceryshopping.common.CheckoutInterface
import eu.javimar.groceryshopping.databinding.CartFragmentBinding
import eu.javimar.groceryshopping.ui.ShopViewModel

@AndroidEntryPoint
class CartFragment: Fragment(), CheckoutInterface {

    private lateinit var binding: CartFragmentBinding
    private val viewModel: ShopViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.cart_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            lifecycleOwner = this@CartFragment
            checkout = this@CartFragment
            cancelButton.setOnClickListener {
                cancelCart()
            }
        }
    }

    override fun checkoutCart() {
        viewModel.resetCart()
        this.findNavController().navigate(CartFragmentDirections
            .actionCartFragmentToEmptyCartFragment())
    }

    private fun cancelCart() {
        this.findNavController().navigate(CartFragmentDirections
            .actionCartFragmentToGroceryListFragment())
    }
}