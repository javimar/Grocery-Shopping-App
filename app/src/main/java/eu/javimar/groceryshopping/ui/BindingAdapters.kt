package eu.javimar.groceryshopping.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.lang.StringBuilder
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