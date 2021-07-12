package eu.javimar.groceryshopping.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import eu.javimar.groceryshopping.R
import eu.javimar.groceryshopping.common.isConnected
import eu.javimar.groceryshopping.common.toast
import eu.javimar.groceryshopping.databinding.GroceryListFragmentBinding
import eu.javimar.groceryshopping.ui.main.GroceryListViewModel.UIModel
import java.text.NumberFormat
import java.util.*

@AndroidEntryPoint
class GroceryListFragment: Fragment() {

    private lateinit var binding: GroceryListFragmentBinding
    private lateinit var adapter: GroceryAdapter
    private val viewModel: GroceryListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View
    {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.grocery_list_fragment, container,false)

        adapter = GroceryAdapter(GroceryAdapter.GroceryClickListener {
            viewModel.onGroceryClicked(it)
        })
        binding.recyclerViewGroceries.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        binding.apply {
            viewmodel = viewModel
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
                adapter.submitList(status.groceries)



                val currency = Currency.getInstance(Locale.getDefault())

                println("JAVIER")

                System.out.println("Symbol: " + currency.getSymbol());

                System.out.println(NumberFormat.getInstance(Locale.getDefault()).format(1.23));


            }

            is UIModel.Navigated -> {
                viewModel.onGroceryNavigated()
            }

            // First entry point
            is UIModel.InitialState -> {
                viewModel.listGroceries()
            }

            is UIModel.Error -> {
                if(requireActivity().isConnected)
                {
                    requireActivity().toast(R.string.err_server, Toast.LENGTH_SHORT)
                }
                else
                {
                    binding.statusImage.setImageResource(R.drawable.ic_connection_error)
                    requireActivity().toast(R.string.err_nointernet, Toast.LENGTH_SHORT)
                }
            }

        }
    }
}