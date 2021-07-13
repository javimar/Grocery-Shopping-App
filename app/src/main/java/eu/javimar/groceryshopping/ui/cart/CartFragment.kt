package eu.javimar.groceryshopping.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import dagger.hilt.android.AndroidEntryPoint
import eu.javimar.domain.Grocery
import eu.javimar.groceryshopping.R
import eu.javimar.groceryshopping.common.CheckoutInterface
import eu.javimar.groceryshopping.databinding.CartFragmentBinding
import eu.javimar.groceryshopping.ui.ShopViewModel
import eu.javimar.groceryshopping.ui.ShopViewModel.UIModel

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
        viewModel.status.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private fun updateUi(status: UIModel) {

        when (status) {
            is UIModel.Loaded -> {
                refreshRecyclerView(status.groceries)
            }
            else -> {} // nothing at the moment
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

    private fun refreshRecyclerView(groceries: List<Grocery>) {

        var totalCart = 0.0
        var mutableList: ArrayList<Grocery>

        val groupAdapter = GroupAdapter<GroupieViewHolder>()
            .apply {
                groceries.groupBy(Grocery::type)
                    .entries.map { (groceryType, groceryList) ->

                        mutableList = groceryList as ArrayList<Grocery>

                        val section = Section()
                        var total = 0.0

                        with(mutableList.iterator()) {
                            forEach { item ->
                                total += item.price * item.quantity
                                if(item.quantity == 0) remove()
                            }
                        }
                        totalCart += total

                        if(total != 0.0) {
                            section.setHeader(CartHeader(groceryType, total))
                            section.addAll(mutableList.toGroceryItem())
                            this.add(section)
                        }
                    }
                binding.checkoutTotal = totalCart
            }

        binding.recyclerViewCart.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
            adapter = groupAdapter
        }
    }

    // Extension
    private fun List<Grocery>.toGroceryItem(): List<CartBody> {
        return this.map {
            CartBody(it)
        }
    }
}