package eu.javimar.groceryshopping.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.javimar.domain.Grocery
import eu.javimar.usecases.GetGroceryList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroceryListViewModel @Inject constructor(
    private val getGroceryList: GetGroceryList): ViewModel() {





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

}