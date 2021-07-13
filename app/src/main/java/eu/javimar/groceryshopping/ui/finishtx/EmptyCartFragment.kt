package eu.javimar.groceryshopping.ui.finishtx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import eu.javimar.groceryshopping.R
import eu.javimar.groceryshopping.databinding.EmptyCartFragmentBinding

class EmptyCartFragment: Fragment() {

    private lateinit var binding: EmptyCartFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.empty_cart_fragment, container, false)
        return binding.root
    }
}