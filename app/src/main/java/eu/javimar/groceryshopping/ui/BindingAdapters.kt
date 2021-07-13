package eu.javimar.groceryshopping.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import eu.javimar.domain.Grocery
import eu.javimar.groceryshopping.R
import java.text.NumberFormat
import java.util.*

@BindingAdapter("formatCurrency")
fun TextView.formatCurrency(value: Double)
{
    val sb = StringBuilder()
    val currency = Currency.getInstance(Locale.getDefault())

    // we could check for US locale and prefix the $
    sb.apply {
        text = append(NumberFormat.getInstance(Locale.getDefault()).format(value))
            .append(currency.symbol)
    }
}

@BindingAdapter("calcPricePerQuantity")
fun TextView.calcPricePerQuantity(grocery: Grocery)
{
    val sb = StringBuilder()
    sb.apply {
        text = append(grocery.name).append(" x ").append(grocery.quantity)
    }
}

@BindingAdapter("calcTotalCart")
fun TextView.calcTotalCart(value: Double)
{
    val sb = StringBuilder()
    val currency = Currency.getInstance(Locale.getDefault())

    sb.apply {
        text = append(context.getString(R.string.checkout_item_button)).append(" (")
            .append(NumberFormat.getInstance(Locale.getDefault()).format(value))
            .append(currency.symbol)
            .append(")")
    }
}

@BindingAdapter("showImage")
fun ImageView.showImage(value: Double)
{
    visibility = if(value <= 0.0) View.VISIBLE
    else View.GONE
}

@BindingAdapter("makeVisible")
fun View.makeVisible(value: Double)
{
    visibility = if(value <= 0.0) View.GONE
    else View.VISIBLE
}
