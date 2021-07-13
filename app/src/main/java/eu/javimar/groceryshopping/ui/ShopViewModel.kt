package eu.javimar.groceryshopping.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.javimar.domain.Grocery
import eu.javimar.usecases.AddItemToCart
import eu.javimar.usecases.GetGroceryList
import eu.javimar.usecases.SubstractItemFromCart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val getGroceryList: GetGroceryList,
    private val addItemToCart: AddItemToCart,
    private val substractItemFromCart: SubstractItemFromCart): ViewModel() {




    private val _status = MutableLiveData<UIModel>()
    val status: LiveData<UIModel>
        get() {
            if(_status.value == null)  refresh()
            return _status
        }

    sealed class UIModel
    {
        object InitialState : UIModel()
        object Loading : UIModel()
        data class Loaded(val groceries: List<Grocery>) : UIModel()
        object Error : UIModel()
    }

    fun listGroceries()
    {
        viewModelScope.launch {
            _status.value = UIModel.Loading
            try
            {
                _status.value = UIModel.Loaded(getGroceryList.invoke())
            }
            catch (e: Exception)
            {
                _status.value = UIModel.Error
            }
        }
    }

    private fun refresh()
    {
        _status.value = UIModel.InitialState
    }

    fun addItemToCart(id: Int) {
        viewModelScope.launch {
            addItemToCart.invoke(id)
        }
    }

    fun substractItemFromCart(id: Int) {
        viewModelScope.launch {
            substractItemFromCart.invoke(id)
        }
    }



}