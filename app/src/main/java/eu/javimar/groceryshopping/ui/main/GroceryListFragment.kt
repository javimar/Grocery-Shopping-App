package eu.javimar.groceryshopping.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.*
import dagger.hilt.android.AndroidEntryPoint
import eu.javimar.domain.Grocery
import eu.javimar.groceryshopping.R
import eu.javimar.groceryshopping.common.ShopInterface
import eu.javimar.groceryshopping.common.isConnected
import eu.javimar.groceryshopping.common.toast
import eu.javimar.groceryshopping.databinding.GroceryListFragmentBinding
import eu.javimar.groceryshopping.ui.ShopViewModel
import eu.javimar.groceryshopping.ui.ShopViewModel.UIModel

@AndroidEntryPoint
class GroceryListFragment : Fragment(), ShopInterface {

    private lateinit var binding: GroceryListFragmentBinding
    private val viewModel: ShopViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.grocery_list_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            //viewmodel = viewModel
            lifecycleOwner = this@GroceryListFragment
        }
        viewModel.status.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private fun updateUi(status: UIModel) {

        when (status) {
            is UIModel.Loading -> {
                binding.statusImage.setImageResource(R.drawable.loading_animation)
            }

            is UIModel.Loaded -> {
                binding.statusImage.visibility = GONE
                initRecyclerView(status.groceries)
            }

            // First entry point
            is UIModel.InitialState -> {
                viewModel.listGroceries()
            }

            is UIModel.Error -> {
                if (requireActivity().isConnected) {
                    requireActivity().toast(R.string.err_server, Toast.LENGTH_SHORT)
                } else {
                    //binding.statusImage.setImageResource(R.drawable.ic_connection_error)
                    requireActivity().toast(R.string.err_nointernet, Toast.LENGTH_SHORT)
                }
            }
        }
    }

    private fun initRecyclerView(groceries: List<Grocery>) {

        val groupAdapter = GroupAdapter<GroupieViewHolder>()
            .apply {
                groceries.groupBy(Grocery::type)
                    .entries.map { (groceryType, groceryList) ->
                        val section = Section()
                        section.setHeader(GroceryHeader(groceryType, 0.0))
                        section.addAll(groceryList.toGroceryItem())
                        this.add(section)
                    }
            }

        binding.recyclerViewGroceries.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
            adapter = groupAdapter
        }
    }

    // Extension
    private fun List<Grocery>.toGroceryItem(): List<GroceryBody> {
        return this.map {
            GroceryBody(it, this@GroceryListFragment)
        }
    }

    override fun addGrocery(grocery: Grocery) {

    }

    override fun deleteGrocery(grocery: Grocery) {

    }
}