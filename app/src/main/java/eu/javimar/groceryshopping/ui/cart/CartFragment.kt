package eu.javimar.groceryshopping.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import eu.javimar.groceryshopping.R
import eu.javimar.groceryshopping.databinding.CartFragmentBinding

@AndroidEntryPoint
class CartFragment: Fragment() {

    private lateinit var binding: CartFragmentBinding

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
            //viewmodel = viewModel
            lifecycleOwner = this@CartFragment
        }


    }
}